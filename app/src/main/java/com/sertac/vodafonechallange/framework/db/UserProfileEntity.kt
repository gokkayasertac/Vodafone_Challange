package com.sertac.vodafonechallange.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userProfile")
data class UserProfileEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,

    @ColumnInfo(name = "login")
    val login: String? = null,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: String? = null,

    @ColumnInfo(name = "following")
    val following: Int? = null,

    @ColumnInfo(name = "followers")
    val followers: Int? = null,

    @ColumnInfo(name = "company")
    val company: String? = null,

    @ColumnInfo(name = "location")
    val location: String? = null,

    @ColumnInfo(name = "repos_url")
    val reposUrl: String? = null,

    @ColumnInfo(name = "public_repos")
    val publicRepos: Int? = null,

    @ColumnInfo(name = "public_gists")
    val publicGists: Int? = null,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

)
