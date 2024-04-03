package com.latif.vidio.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_user")
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    val idUser: String? = null,

    @Column(name = "user_name")
    var userName: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    var userType: TypeUserEntity? = null,

    @OneToMany(mappedBy = "id_user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var favorites: MutableList<FavoriteEntity> = mutableListOf()

)