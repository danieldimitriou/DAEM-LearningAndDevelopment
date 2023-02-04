package gr.athtech.daem.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.trace("Loaded {}.", getClass());
	}

	@PreDestroy
	public void destroy() {
		logger.trace("{} is about to be destroyed.", getClass().getName());
	}
}
