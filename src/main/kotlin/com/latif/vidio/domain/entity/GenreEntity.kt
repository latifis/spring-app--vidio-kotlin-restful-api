package com.latif.vidio.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_genre")
data class GenreEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    val idGenre:String? = null,

    @Column(name = "genre_name")
    var genreName:String? = null,

    @OneToMany(mappedBy = "id_vidio", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var vidios: MutableList<VidioEntity> = mutableListOf()

)
