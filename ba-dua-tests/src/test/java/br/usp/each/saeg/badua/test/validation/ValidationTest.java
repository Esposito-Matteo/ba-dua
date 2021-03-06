/**
 * Copyright (c) 2014, 2020 University of Sao Paulo and Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Roberto Araujo - initial API and implementation and/or initial documentation
 */
package br.usp.each.saeg.badua.test.validation;

import java.io.IOException;

import br.usp.each.saeg.badua.agent.rt.internal.RT;
import br.usp.each.saeg.badua.core.instr.Instrumenter;

public abstract class ValidationTest {

    protected ValidationTestClassLoader loader;

    protected boolean exceptionHandler = true;

    public void setUp() throws Exception {
        loader = new ValidationTestClassLoader();
    }

    public Class<?> addClass(final String name, final byte[] bytes) {
        return loader.add(name, instrument(name, bytes));
    }

    private byte[] instrument(final String name, final byte[] bytes) {
        final Instrumenter instrumenter = new Instrumenter(RT.class.getName(), exceptionHandler);

        try {
            return instrumenter.instrument(bytes, name);
        } catch (final IOException ignore) {
            /* never happens */
            throw new RuntimeException(ignore);
        }
    }

}
