package com.hawk.ecom.task.service;

import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.ChargeDataService.ConstChargeNotifyStatus;
import com.hawk.ecom.task.request.ChargeDataNotifyRequest;

@Service
public class ChargeDataTaskService {
	public void notify(ChargeDataNotifyRequest chargeDataNotifyRequest) {
		if (ConstChargeNotifyStatus.SUCCESS.equals(chargeDataNotifyRequest.getStatus())) {

			return;
		}

		if (ConstChargeNotifyStatus.FAILURE.equals(chargeDataNotifyRequest.getStatus())) {

			return;
		}

		if (ConstChargeNotifyStatus.PROCESSING.equals(chargeDataNotifyRequest.getStatus())) {

			return;
		}
	}
}
