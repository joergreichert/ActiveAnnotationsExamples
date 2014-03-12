/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.example.domainmodel.validation;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelPackage;
import org.eclipse.xtext.example.domainmodel.domainmodel.Entity;
import org.eclipse.xtext.example.domainmodel.domainmodel.Feature;
import org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.validation.XbaseJavaValidator;

public class DomainmodelJavaValidator extends XbaseJavaValidator {

	@Check
	public void checkTypeNameStartsWithCapital(Entity entity) {
		if (!Character.isUpperCase(entity.getName().charAt(0))) {
			warning(IssueCodes.getMessageForINVALID_TYPE_NAME(entity.getName()),
					DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
					IssueCodes.INVALID_TYPE_NAME, entity.getName());
		}
	}

	@Check
	public void checkFeatureNameStartsWithLowercase(Feature feature) {
		if (!Character.isLowerCase(feature.getName().charAt(0))) {
			warning(IssueCodes.getMessageForINVALID_FEATURE_NAME(((Entity) feature.eContainer()).getName(),
					feature.getName()), DomainmodelPackage.Literals.FEATURE__NAME,
					ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.INVALID_FEATURE_NAME, feature.getName());
		}
	}

	@Check
	public void checkPackage(PackageDeclaration packages) {
		if (Strings.isEmpty(packages.getName())) {
			error(IssueCodes.getMessageForEMPTY_PACKAGE_NAME(), DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME);
		}
		if (packages.getName().equals("java")) {
			error(IssueCodes.getMessageForINVALID_PACKAGE_NAME(packages.getName()),
					DomainmodelPackage.Literals.ABSTRACT_ELEMENT__NAME);
		}
	}

	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> ePackages = super.getEPackages();
		ePackages.add(DomainmodelPackage.eINSTANCE);
		return ePackages;
	}
}
