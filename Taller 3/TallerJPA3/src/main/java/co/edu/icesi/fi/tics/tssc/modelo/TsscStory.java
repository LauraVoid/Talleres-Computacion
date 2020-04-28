package co.edu.icesi.fi.tics.tssc.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the TSSC_STORY database table.
 * 
 */
@Entity
@Table(name = "TSSC_STORY")
@NamedQuery(name = "TsscStory.findAll", query = "SELECT t FROM TsscStory t")
public class TsscStory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TSSC_STORY_ID_GENERATOR", allocationSize = 1, sequenceName = "TSSC_STORY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSSC_STORY_ID_GENERATOR")
	private long id;

	@Column(name = "ALT_DESC_SHOWN")
	//@NotBlank(message= "Alt description Shown es obligatorio")
	private String altDescShown;

	@Column(name = "ALT_DESCRIPTON")
	//@NotBlank(message= "Alt description es obligatorio")
	private String altDescripton;

	@Column(name = "BUSINESS_VALUE")
	@NotNull(message= "El valor de negocio es obligatorio")
	@Min(value=1, message="El valor de negocio debe ser minimo 1")
	private BigDecimal businessValue;

	private String description;

	@Column(name = "INITIAL_SPRINT")
	@Min(value=1, message="El valor inicial de sprint debe ser minimo 1")
	@NotNull(message= "El valor inicial de sprint es obligatorio")
	private BigDecimal initialSprint;

	@Column(name = "ST_NUMBER")
	
	private BigDecimal number;

	@NotNull(message= "La prioridad es obligatoria")
	@Min(value=1 , message="La prioridad debe ser minimo 1")
	private BigDecimal priority;

	@Column(name = "SHORT_DESCRIPTION")
	//@NotBlank(message= "La decripción corta es obligatoria")
	private String shortDescription;

	// bi-directional many-to-one association to TsscAcceptanceCriteria
	@OneToMany(mappedBy = "tsscStory")
	private List<TsscAcceptanceCriteria> tsscAcceptanceCriterias;

	// bi-directional many-to-one association to TsscDeliverable
	@OneToMany(mappedBy = "tsscStory")
	private List<TsscDeliverable> tsscDeliverables;

	// bi-directional many-to-one association to TsscGame
	@ManyToOne
	@JoinColumn(name = "TSSC_GAME_ID")
	@NotNull(message= "El juego asociado es obligatorio")
	private TsscGame tsscGame;
	
	//bi-directional many-to-one association to TsscTopic
	@ManyToOne
	@JoinColumn(name="TSSC_TOPIC_ID")
	private TsscTopic tsscTopic;


	// bi-directional many-to-one association to TsscState
	@ManyToOne
	@JoinColumn(name = "TSSC_STATE_ID")
	private TsscState tsscState;
	
	

	public TsscStory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAltDescShown() {
		return this.altDescShown;
	}

	public void setAltDescShown(String altDescShown) {
		this.altDescShown = altDescShown;
	}

	public String getAltDescripton() {
		return this.altDescripton;
	}

	public void setAltDescripton(String altDescripton) {
		this.altDescripton = altDescripton;
	}

	public BigDecimal getBusinessValue() {
		return this.businessValue;
	}

	public void setBusinessValue(BigDecimal businessValue) {
		this.businessValue = businessValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getInitialSprint() {
		return this.initialSprint;
	}

	public void setInitialSprint(BigDecimal initialSprint) {
		this.initialSprint = initialSprint;
	}

	public BigDecimal getNumber() {
		return this.number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public List<TsscAcceptanceCriteria> getTsscAcceptanceCriterias() {
		return this.tsscAcceptanceCriterias;
	}

	public void setTsscAcceptanceCriterias(List<TsscAcceptanceCriteria> tsscAcceptanceCriterias) {
		this.tsscAcceptanceCriterias = tsscAcceptanceCriterias;
	}

	public TsscAcceptanceCriteria addTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().add(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscStory(this);

		return tsscAcceptanceCriteria;
	}

	public TsscAcceptanceCriteria removeTsscAcceptanceCriteria(TsscAcceptanceCriteria tsscAcceptanceCriteria) {
		getTsscAcceptanceCriterias().remove(tsscAcceptanceCriteria);
		tsscAcceptanceCriteria.setTsscStory(null);

		return tsscAcceptanceCriteria;
	}

	public List<TsscDeliverable> getTsscDeliverables() {
		return this.tsscDeliverables;
	}

	public void setTsscDeliverables(List<TsscDeliverable> tsscDeliverables) {
		this.tsscDeliverables = tsscDeliverables;
	}

	public TsscDeliverable addTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().add(tsscDeliverable);
		tsscDeliverable.setTsscStory(this);

		return tsscDeliverable;
	}

	public TsscDeliverable removeTsscDeliverable(TsscDeliverable tsscDeliverable) {
		getTsscDeliverables().remove(tsscDeliverable);
		tsscDeliverable.setTsscStory(null);

		return tsscDeliverable;
	}

	public TsscGame getTsscGame() {
		return this.tsscGame;
	}

	public void setTsscGame(TsscGame tsscGame) {
		this.tsscGame = tsscGame;
	}
	
	public TsscTopic getTsscTopic() {
		return this.tsscTopic;
	}

	public void setTsscTopic(TsscTopic tsscTopic) {
		this.tsscTopic = tsscTopic;
	}

	public TsscState getTsscState() {
		return this.tsscState;
	}

	public void setTsscState(TsscState tsscState) {
		this.tsscState = tsscState;
	}

}