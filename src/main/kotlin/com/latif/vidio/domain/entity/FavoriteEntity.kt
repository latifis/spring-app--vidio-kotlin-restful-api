package com.latif.vidio.domain.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "mst_favorite")
data class FavoriteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    val idFavorite:String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vidio")
    var idVidio: VidioEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    var idUser: UserEntity? = null,

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_added")
    var dtAdded: Date? = null,

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_updated")
    var dtUpdated: Date? = null

)
