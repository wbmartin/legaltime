// ______________________________________________________
// Generated by sql2java - http://sql2java.sourceforge.net/
// jdbc driver used at code generation time: com.mysql.jdbc.Driver
//
// Please help us improve this tool by reporting:
// - problems and suggestions to
//   http://sourceforge.net/tracker/?group_id=54687
// - feedbacks and ideas on
//   http://sourceforge.net/forum/forum.php?forum_id=182208
// ______________________________________________________

package legaltime.model;

import java.util.HashMap;

import legaltime.model.exception.DAOException;

/**
 * This cache manager is based on code from Mark GRAND in "Patterns in Java,
 * Volume 1", Wiley.
 * 
 * @author afagot
 */
public final class PaymentLogCache {
	/**
	 * Unique instance of the cache manager.
	 */
	private static PaymentLogCache instance;

	/**
	 * Maximum number of PaymentLogBean objects that may be in the cache.
	 */
	private static final int MAX_CACHE_SIZE = 80;

	/**
	 * The number of PaymentLogBean objects currently in the cache.
	 */
	private int currentCacheSize = 0;

	/**
	 * We use a linked list to determine the least recently used bean. The cache
	 * is implemented by a HashMap object. The HashMap values are linked list
	 * objects that refer to the actual PaymentLogBean.
	 */
	private HashMap cache = new HashMap();

	/**
	 * This is the head of the linked list that refers to the most recently used
	 * PaymentLogBean.
	 */
	private LinkedList mru;

	/**
	 * This is the end of the linked list that refers to the least recently used
	 * PaymentLogBean.
	 */
	private LinkedList lru;
	
	/**
	 * In order to prevent the compiler from generating the default constructor.
	 */
	private PaymentLogCache() {
	}
	
	/**
	 * Return the unique instance of the class.
	 */
	public static PaymentLogCache getInstance() {
		if (null == instance) {
			instance = new PaymentLogCache();
		}
		return instance;
	}

	/**
	 * A PaymentLogBean object is passed to this method for addition to the cache.
	 */
	public void addPaymentLog(PaymentLogBean bean) {
		Integer id = bean.getPaymentLogId();
		if (null == cache.get(id)) { // bean not in the cache
			// add bean to the cache, making it the most recently used
			if (0 == currentCacheSize) {
				// treat empty cache as a special case
				lru = mru = new LinkedList();
				mru.bean = bean;
			} else {
				LinkedList newLink;
				if (MAX_CACHE_SIZE < currentCacheSize) {
					// remove the least recently used bean from the cache
					newLink = lru;
					lru = newLink.previous;
					cache.remove(newLink.bean.getPaymentLogId());
					lru.next = null;
				} else {
					newLink = new LinkedList();
					currentCacheSize++;
				}
				newLink.bean = bean;
				newLink.next = mru;
				newLink.previous = null;
				mru = newLink;
			}
			// put the now most recently used bean in the cache
			cache.put(id, mru);
		} else { // bean already in the cache
			// addBid should not be called when the object is already in the
			// cache however since that happened do a fetch so that the object
			// becomes the most recently used
			fetchPaymentLog(id);
		}
	}

	/**
	 * Return the PaymentLogBean associated with the given key.
	 */
	public PaymentLogBean fetchPaymentLog(Integer id) {
		LinkedList foundLink = (LinkedList) cache.get(id);
		if (null == foundLink) {
			PaymentLogBean bean = null;
			try {
				bean = PaymentLogManager.getInstance().loadByPrimaryKey(id);
			} catch (DAOException e) {
				return null;
			}
			if (null != bean) {
				addPaymentLog(bean);
			}
			return bean;
		}
		// AFagot [2007-07-06]
		// this could be acheived with the now standard java.util.LinkedList and
		// java.util.ListIterator but the code was already written since 1998 ;-)
		if (mru != foundLink) {
			if (null != foundLink.previous) {
				foundLink.previous.next = foundLink.next;
			}
			if (null != foundLink.next) {
				foundLink.next.previous = foundLink.previous;
			}
			foundLink.previous = null;
			foundLink.next = mru;
			mru = foundLink;
		}
		return foundLink.bean;
	}

	/**
	 * Private doubly linked list for managing list of most recently used beans.
	 */
	private class LinkedList {
		PaymentLogBean bean;
		LinkedList previous;
		LinkedList next;
	}
}
