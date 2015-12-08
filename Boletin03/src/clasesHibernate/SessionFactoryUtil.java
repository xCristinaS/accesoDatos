package clasesHibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	/**
	 * La f�brica de sesiones.
	 */
	private static final SessionFactory sessionFactory;
	
	static {
		/* Configuraci�n */
		Configuration config = new Configuration();
		config.configure();
		
		/* Constructor del Registro de Servicios,
		/* y posterior configuraci�n */
		StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder();
		serviceRegistry.applySettings(config.getProperties());
		
		/* Creamos la f�brica de sesiones de una vez,
		 * pas�ndole por constructor el Registro de Servicios. */
		sessionFactory = config.buildSessionFactory(serviceRegistry.build());
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
