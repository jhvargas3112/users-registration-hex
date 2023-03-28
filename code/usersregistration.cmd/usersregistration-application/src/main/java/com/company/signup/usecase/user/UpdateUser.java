package com.company.signup.usecase.user;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.statisctic.GetBirthYearAvgBmiByYearRepository;
import com.company.signup.domain.repository.statisctic.GetBirthYearUsersTotalByYearRepository;
import com.company.signup.domain.repository.statisctic.UpdateBirthYearAvgBmiRepository;
import com.company.signup.domain.repository.user.GetUserByIdRepository;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import com.company.signup.domain.usecase.user.UpdateUserUseCase;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser implements UpdateUserUseCase {

  private final GetUserByIdRepository getUserByIdRepository;

  private final UpdateUserRepository updateUserRepository;

  private final GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository;

  private final GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository;

  private final UpdateBirthYearAvgBmiRepository updateBirthYearAvgBmiRepository;

  public UpdateUser(GetUserByIdRepository getUserByIdRepository,
      UpdateUserRepository updateUserRepository,
      GetBirthYearAvgBmiByYearRepository getBirthYearAvgBmiByYearRepository,
      GetBirthYearUsersTotalByYearRepository getBirthYearUsersTotalByYearRepository,
      UpdateBirthYearAvgBmiRepository updateBirthYearAvgBmiRepository) {
    this.getUserByIdRepository = getUserByIdRepository;
    this.updateUserRepository = updateUserRepository;
    this.getBirthYearAvgBmiByYearRepository = getBirthYearAvgBmiByYearRepository;
    this.getBirthYearUsersTotalByYearRepository = getBirthYearUsersTotalByYearRepository;
    this.updateBirthYearAvgBmiRepository = updateBirthYearAvgBmiRepository;
  }

  @Override
  public User execute(User user) {
    var userBeforeUpdate = getUserByIdRepository.execute(user.getId());
    var userAfterUpdate = updateUserRepository.execute(user);

    Optional.ofNullable(userAfterUpdate)
        .ifPresent(y -> userBeforeUpdate.ifPresent(x -> setBirthYearAvgBmi(x, y)));

    return userAfterUpdate;
  }

  private void setBirthYearAvgBmi(User userBeforeUpdate, User userAfterUpdate) {
    var year = userAfterUpdate.getBirthDate().getYear();
    var userBmiAfterUpdate = userAfterUpdate.calculateBmiValue();
    var userBmiBeforeUpdate = userBeforeUpdate.calculateBmiValue();

    var currentBirthYearAvgBmi = getBirthYearAvgBmiByYearRepository.execute(year);

    currentBirthYearAvgBmi.ifPresent(x -> getBirthYearUsersTotalByYearRepository.execute(year)
        .ifPresent(y -> updateBirthYearAvgBmiRepository.execute(BirthYearAvgBmi.create(year,
            (((x.getAvgBmi() * y.getTotal()) - userBmiBeforeUpdate) + userBmiAfterUpdate)
                / y.getTotal()))));
  }

}
