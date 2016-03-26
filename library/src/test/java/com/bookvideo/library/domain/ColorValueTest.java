package com.bookvideo.library.domain;


import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Theories.class)
public class ColorValueTest {

    @DataPoints
    public static Fixture[] fixtures = {
            new Fixture("rgba(255,255,255,1)", 0xffffffff),
            new Fixture("rgba( 255 , 255 , 255 , 1.0 )", 0xffffffff),
            new Fixture("rgba(255,255,255,0.0)", 0x00ffffff),
            new Fixture("rgba(255,255,255,0)", 0x00ffffff),
            new Fixture("rgba(255,255,255,0.5)", 0x7fffffff),
            new Fixture("rgba(127,0,0,1.0)", 0xff7f0000),
            new Fixture("rgba(0,127,0,1.0)", 0xff007f00),
            new Fixture("rgba(0,0,127,1.0)", 0xff00007f),
            new Fixture("#ffffffff", 0xffffffff),
            new Fixture("#00ffffff", 0x00ffffff),
            new Fixture("#00FFFFFF", 0x00ffffff),
            new Fixture("#10203040", 0x10203040),
            new Fixture("0xffffffff", 0xffffffff),
            new Fixture("0x00ffffff", 0x00ffffff),
            new Fixture("0x00FFFFFF", 0x00ffffff),
            new Fixture("0x10203040", 0x10203040),
    };

    @Theory
    public void colorTest(Fixture fixture) throws Exception {
        ColorValue suv = new ColorValue(fixture.input);

        assertThat(suv.getColor())
                .as("verify %s", fixture.input)
                .isEqualTo(fixture.expect);

    }

    static class Fixture {
        public String input;
        public int expect;

        public Fixture(String rgba, int expect) {
            this.input = rgba;
            this.expect = expect;
        }

    }
}