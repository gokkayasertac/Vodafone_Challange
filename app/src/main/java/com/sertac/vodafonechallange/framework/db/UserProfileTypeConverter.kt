package com.sertac.vodafonechallange.framework.db

import androidx.room.TypeConverter
import com.sertac.core.domain.userprofile.UserProfileResponse

class UserProfileTypeConverter {
    @TypeConverter
    fun modelToEntity(userProfileResponse: UserProfileResponse): UserProfileEntity {
        return UserProfileEntity(
            id = userProfileResponse.id,
            login = userProfileResponse.login,
            avatarUrl = userProfileResponse.avatarUrl,
            name = userProfileResponse.name,
            createdAt = userProfileResponse.createdAt,
            following = userProfileResponse.following,
            followers = userProfileResponse.followers,
            company = userProfileResponse.company,
            location = userProfileResponse.location,
            reposUrl = userProfileResponse.reposUrl,
            publicRepos = userProfileResponse.publicRepos,
            publicGists = userProfileResponse.publicGists,
            updatedAt = userProfileResponse.updatedAt,
            type = userProfileResponse.type,
            email = userProfileResponse.email,

        )
    }

    @TypeConverter
    fun entityToModel(userProfileEntity: UserProfileEntity): UserProfileResponse {
        return UserProfileResponse(
            id = userProfileEntity.id,
            login = userProfileEntity.login,
            avatarUrl = userProfileEntity.avatarUrl,
            name = userProfileEntity.name,
            createdAt = userProfileEntity.createdAt,
            following = userProfileEntity.following,
            followers = userProfileEntity.followers,
            company = userProfileEntity.company,
            location = userProfileEntity.location,
            reposUrl = userProfileEntity.reposUrl,
            publicRepos = userProfileEntity.publicRepos,
            publicGists = userProfileEntity.publicGists,
            updatedAt = userProfileEntity.updatedAt,
            type = userProfileEntity.type,
            email = userProfileEntity.email,
            )
    }
}
//@TypeConverters(LoginConverter::class) to database class