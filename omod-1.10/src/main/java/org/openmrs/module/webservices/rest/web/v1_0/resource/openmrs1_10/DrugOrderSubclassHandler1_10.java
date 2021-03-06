/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_10;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openmrs.CareSetting;
import org.openmrs.DrugOrder;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.ConversionUtil;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_8.DrugOrderSubclassHandler1_8;

/**
 * Exposes the {@link org.openmrs.DrugOrder} subclass as a type in
 * {@link org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_10.DrugOrderSubclassHandler1_10}
 */
@Handler(supports = DrugOrder.class)
public class DrugOrderSubclassHandler1_10 extends DrugOrderSubclassHandler1_8 {
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#newDelegate()
	 */
	@Override
	public DrugOrder newDelegate() {
		return new DrugOrder();
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingSubclassHandler#getAllByType(org.openmrs.module.webservices.rest.web.RequestContext)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public PageableResult getAllByType(RequestContext context) throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getRepresentationDescription(org.openmrs.module.webservices.rest.web.representation.Representation)
	 */
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			OrderResource1_10 orderResource = (OrderResource1_10) Context.getService(RestService.class)
			        .getResourceBySupportedClass(Order.class);
			DelegatingResourceDescription d = orderResource.getRepresentationDescription(rep);
			d.addProperty("drug", Representation.REF);
			d.addProperty("dosingType");
			d.addProperty("dose");
			d.addProperty("doseUnits", Representation.REF);
			d.addProperty("frequency", Representation.REF);
			d.addProperty("asNeeded");
			d.addProperty("asNeededCondition");
			d.addProperty("quantity");
			d.addProperty("quantityUnits", Representation.REF);
			d.addProperty("numRefills");
			d.addProperty("administrationInstructions");
			d.addProperty("dosingInstructions");
			d.addProperty("duration");
			d.addProperty("durationUnits", Representation.REF);
			d.addProperty("route", Representation.REF);
			return d;
		} else if (rep instanceof FullRepresentation) {
			OrderResource1_10 orderResource = (OrderResource1_10) Context.getService(RestService.class)
			        .getResourceBySupportedClass(Order.class);
			DelegatingResourceDescription d = orderResource.getRepresentationDescription(rep);
			d.addProperty("drug", Representation.REF);
			d.addProperty("dosingType");
			d.addProperty("dose");
			d.addProperty("doseUnits", Representation.DEFAULT);
			d.addProperty("frequency", Representation.REF);
			d.addProperty("asNeeded");
			d.addProperty("asNeededCondition");
			d.addProperty("quantity");
			d.addProperty("quantityUnits", Representation.DEFAULT);
			d.addProperty("numRefills");
			d.addProperty("administrationInstructions");
			d.addProperty("dosingInstructions");
			d.addProperty("duration");
			d.addProperty("durationUnits", Representation.DEFAULT);
			d.addProperty("route", Representation.REF);
			return d;
		}
		return null;
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getCreatableProperties()
	 */
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		OrderResource1_10 orderResource = (OrderResource1_10) Context.getService(RestService.class)
		        .getResourceBySupportedClass(Order.class);
		DelegatingResourceDescription d = orderResource.getCreatableProperties();
		d.addProperty("drug");
		d.addProperty("dosingType");
		d.addProperty("dose");
		d.addProperty("doseUnits");
		d.addProperty("frequency");
		d.addProperty("asNeeded");
		d.addProperty("asNeededCondition");
		d.addProperty("quantity");
		d.addProperty("quantityUnits");
		d.addProperty("numRefills");
		d.addProperty("administrationInstructions");
		d.addProperty("dosingInstructions");
		d.addProperty("duration");
		d.addProperty("durationUnits");
		d.addProperty("route");
		return d;
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceHandler#getUpdatableProperties()
	 */
	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		OrderResource1_10 orderResource = (OrderResource1_10) Context.getService(RestService.class)
		        .getResourceBySupportedClass(Order.class);
		//this actually throws a ResourceDoesNotSupportOperationException
		return orderResource.getUpdatableProperties();
	}
	
	public PageableResult getActiveOrders(Patient patient, RequestContext context) {
		String careSettingUuid = context.getRequest().getParameter("careSetting");
		String asOfDateString = context.getRequest().getParameter("asOfDate");
		CareSetting careSetting = null;
		java.util.Date asOfDate = null;
		if (StringUtils.isNotBlank(asOfDateString)) {
			asOfDate = (java.util.Date) ConversionUtil.convert(asOfDateString, java.util.Date.class);
		}
		if (StringUtils.isNotBlank(careSettingUuid)) {
			careSetting = ((CareSettingResource1_10) Context.getService(RestService.class).getResourceBySupportedClass(
			    CareSetting.class)).getByUniqueId(careSettingUuid);
		}
		List<DrugOrder> drugOrders = Context.getOrderService().getActiveOrders(patient, DrugOrder.class, careSetting,
		    asOfDate);
		return new NeedsPaging<DrugOrder>(drugOrders, context);
	}
	
	/**
	 * @see OrderResource1_10#getDisplayString(org.openmrs.Order)
	 */
	@PropertyGetter("display")
	public static String getDisplay(DrugOrder delegate) {
		StringBuilder ret = new StringBuilder();
		if (delegate.getDrug() != null) {
			ret.append(delegate.getDrug().getName());
			ret.append(": ");
			if (delegate.getDose() != null) {
				ret.append(delegate.getDose());
				if (delegate.getDoseUnits() != null && delegate.getDoseUnits().getName() != null) {
					ret.append(delegate.getDoseUnits().getName().getName());
				} else {
					ret.append("[no units]");
				}
			} else {
				ret.append("[no dose]");
			}
		} else {
			OrderResource1_10 orderResource = (OrderResource1_10) Context.getService(RestService.class)
			        .getResourceBySupportedClass(Order.class);
			orderResource.getDisplayString(delegate);
		}
		
		return ret.toString();
	}
}
