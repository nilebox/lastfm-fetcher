package ru.nilebox.lastfm.fetcher.exceptions;

/**
 *
 * @author nile
 */
public class LastFmInvalidConfigException extends Exception {
	private static final long serialVersionUID = 9068706332985050334L;

	public LastFmInvalidConfigException() {
	}

	public LastFmInvalidConfigException(String message) {
		super(message);
	}

	public LastFmInvalidConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public LastFmInvalidConfigException(Throwable cause) {
		super(cause);
	}
	
}
