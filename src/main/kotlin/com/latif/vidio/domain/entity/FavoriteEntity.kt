package com.latif.vidio.domain.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "mst_favorite")
data class FavoriteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    val idFavorite:Long? = null,

//    @Column(name = "id_vidio")
//    var idVidio: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVidio")
    var idVidio: VidioEntity? = null,

//    @Column(name = "id_user")
//    var idUser: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    var idUser: UserEntity? = null,

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_added")
    var dtAdded: Date? = null,

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_updated")
    var dtUpdated: Date? = null

)
