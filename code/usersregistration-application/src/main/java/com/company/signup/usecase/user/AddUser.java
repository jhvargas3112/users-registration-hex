package com.company.signup.usecase.user;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.statisctic.GetBirthYearAvgBmiByYearRepository;
import com.company.signup.domain.repository.statisctic.GetBirthYearUsersTotalByYearRepository;
import com.company.signup.domain.repository.statisctic.SaveBirthYearAvgBmiRepository;
import com.company.signup.domain.repository.statisctic.SaveBirthYearUsersTotalRepository;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearAvgBmiRepository;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearUsersTotalRepository;
import com.company.signup.domain.repository.user.AddUserRepository;
import com.company.signup.domain.usecase.user.AddUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class AddUser implements AddUserUseCase {

  private final AddUserRepository addUserRepository;

  private final GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository;

  private final GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository;

  private final SaveBirthYearUsersTotalRepository saveBirthYearUsersTotalRepository;

  private final UpdateBirthYearUsersTotalRepository updateBirthYearUsersTotalRepository;

  private final SaveBirthYearAvgBmiRepository saveBirthYearAvgBmiRepository;

  private final UpdateBirthYearAvgBmiRepository updateBirthYearAvgBmiRepository;

  public AddUser(AddUserRepository addUserRepository,
      GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository,
      GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository,
      SaveBirthYearUsersTotalRepository saveBirthYearUsersTotalRepository,
      UpdateBirthYearUsersTotalRepository updateBirthYearUsersTotalRepository,
      SaveBirthYearAvgBmiRepository saveBirthYearAvgBmiRepository,
      UpdateBirthYearAvgBmiRepository updateBirthYearAvgBmiRepository) {
    this.addUserRepository = addUserRepository;
    this.getBirthYearAvgBmiByYearRepository = getBirthYearAvgBmiByYearRepository;
    this.getBirthYearUsersTotalByYearRepository = getBirthYearUsersTotalByYearRepository;
    this.saveBirthYearUsersTotalRepository = saveBirthYearUsersTotalRepository;
    this.updateBirthYearUsersTotalRepository = updateBirthYearUsersTotalRepository;
    this.saveBirthYearAvgBmiRepository = saveBirthYearAvgBmiRepository;
    this.updateBirthYearAvgBmiRepository = updateBirthYearAvgBmiRepository;
  }

  @Override
  public User execute(User user) {
    var savedUser = addUserRepository.execute(user);

    Integer year = savedUser.getBirthDate().getYear();

    getBirthYearUsersTotalByYearRepository.execute(year).ifPresentOrElse(
        b -> updateBirthYearUsersTotalRepository.execute(
            BirthYearUsersTotal.create(year, b.getTotal() + 1)),
        () -> saveBirthYearUsersTotalRepository.execute(BirthYearUsersTotal.create(year, 1)));

    setBirthYearAvgBmi(savedUser);

    return savedUser;
  }

  private void setBirthYearAvgBmi(User user) {
    var year = user.getBirthDate().getYear();
    var bmi = user.calculateBmiValue();
    var birthYearAvgBmi = getBirthYearAvgBmiByYearRepository.execute(year);
    
    birthYearAvgBmi.ifPresentOrElse(
        x -> getBirthYearUsersTotalByYearRepository.execute(year).ifPresent(y -> {
          var currentBirthYearUsersTotal = y.getTotal();
          var currentBirthYearAvgBmi = x.getAvgBmi();
          var newBirthYearAvgBmi = (currentBirthYearAvgBmi * (currentBirthYearUsersTotal - 1) + bmi)
              / currentBirthYearUsersTotal;
          
          updateBirthYearAvgBmiRepository.execute(BirthYearAvgBmi.create(year, newBirthYearAvgBmi));
        }), () -> saveBirthYearAvgBmiRepository.execute(BirthYearAvgBmi.create(year, bmi)));
  }

}
