package org.svj.variance;

public class TestClasses {
    static class Base {
        public final int v;
        Base(int v) {
            this.v = v;
        }
        @Override
        public String toString() {
            return String.format("Base(%d)", v);
        }
    }
    static class Sub extends Base {
        public final int v2;
        Sub(int v, int v2) {
            super(v);
            this.v2 = v2;
        }

        @Override
        public String toString() {
            return String.format("Sub(%d,%d)", v, v2);
        }
    }

    static class Sub2 extends Base {
        public final String v2;
        Sub2(int v, String v2) {
            super(v);
            this.v2 = v2;
        }

        @Override
        public String toString() {
            return String.format("Sub(%d,%s)", v, v2);
        }
    }
}
