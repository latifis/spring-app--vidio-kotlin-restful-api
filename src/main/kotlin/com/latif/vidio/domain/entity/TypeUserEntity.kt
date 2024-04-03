package com.latif.vidio.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_type_user")
data class TypeUserEntity(

    @Id
    @Column(name = "id_type")
    val idType:String? = null,

    @Column(name = "type_user")
    var typeUser:String? = null,

    @OneToMany(mappedBy = "idType", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var users: MutableList<UserEntity> = mutableListOf()

)
