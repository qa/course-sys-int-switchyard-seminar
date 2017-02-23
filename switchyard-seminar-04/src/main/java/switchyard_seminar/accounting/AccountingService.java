package switchyard_seminar.accounting;

import switchyard_seminar.model.Order;

public interface AccountingService {

	AccountResponse account(Order order);
}
