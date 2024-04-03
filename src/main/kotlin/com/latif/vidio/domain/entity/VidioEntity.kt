package com.latif.vidio.domain.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "mst_vidio")
data class VidioEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vidio")
    val idVidio: Long? = null,

    @Column(name = "name_vidio")
    var nameVidio: String? = null,

    @Column(name = "creator_vidio")
    var creatorVidio: String? = null,

//    @Column(name = "type_id")
//    var typeId: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    var typeId: TypeUserEntity? = null,

//    @Column(name = "id_genre")
//    var idGenre: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    var idGenre: GenreEntity? = null,

    @Column(name = "dt_added")
    var dtAdded: Date? = null,

    @Column(name = "dt_updated")
    var dtUpdated: Date? = null,

    @OneToMany(mappedBy = "idVidio", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var favorites: MutableList<FavoriteEntity> = mutableListOf()

)
