package br.com.campingfire.model;

import br.com.campingfire.request.CampingRequest;
import br.com.campingfire.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "campings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Camping implements Serializable {

    private static final String STATES = "'AC', 'AL', 'AP', 'AM', 'BA', 'CE', " +
            "'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', " +
            "'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = ("ENUM(" + STATES + ")"), nullable = false)
    private State state;

    @Column(length = 25, nullable = false)
    private String city;

    @Column(length = 300)
    private String description;

    /* TODO This can be a new table (one to many)*/
    @Column(length = 11, nullable = false)
    private Long contact;

    /* TODO This can be a new table (one to many) */
    @Column(length = 150)
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user_camp"))
    private User user;

    public Camping(CampingRequest campingRequest) {

        this.name = campingRequest.getName();
        this.state = campingRequest.getState();
        this.city = campingRequest.getCity();
        this.description = campingRequest.getDescription();
        this.contact = campingRequest.getContact();
        this.image = campingRequest.getImage();

    }

}