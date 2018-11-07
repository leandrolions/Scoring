package ar.com.nat.scoring.servicesimpl;

public enum EnumRests {
	
	BCU(new BcuRest()),
	EXPERTO(new ExpertoRest()),
	PREFILTRO(new PrefiltroRest()),
	SIMULAR(new SimulaBantotalRest()),
	CREAR(new AltaClienteRest());

	private ConsultasRest rests;
	
	 EnumRests(ConsultasRest rests) {
		this.rests = rests;
	}	 
	 public ConsultasRest getIntr() {
		 return rests;
	 }
	 
}
