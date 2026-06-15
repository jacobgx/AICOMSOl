/*
 * diffraction_patterns.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class diffraction_patterns {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Equation_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/c", true);

    model.param().set("l", "0.1[m]");
    model.param().descr("l", "\u6ce2\u957f");
    model.param().set("k", "2*pi[rad]/l");
    model.param().descr("k", "\u6ce2\u6570");
    model.param().set("u0", "1");
    model.param().descr("u0", "\u6d41\u5165\u8fb9\u754c\u5904\u7684\u5165\u5c04\u6ce2\u5e45\u503c");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0.5, 0});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.5, 0.03});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-0.015-0.1"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", 0.2);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "copy1", "r1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("c").feature("cfeq1").setIndex("a", "-k^2", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", 0, 0);
    model.component("comp1").physics("c").create("flux1", "FluxBoundary", 1);
    model.component("comp1").physics("c").feature("flux1").selection().set(1, 4);
    model.component("comp1").physics("c").feature("flux1").setIndex("g", "-i*k*u+2*u0*i*k*exp(-i*k*x)", 0);
    model.component("comp1").physics("c").create("flux2", "FluxBoundary", 1);
    model.component("comp1").physics("c").feature("flux2").selection().set(11, 12);
    model.component("comp1").physics("c").feature("flux2").setIndex("g", "-i*k*u", 0);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "l/5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").run();

    model.title("\u884d\u5c04\u56fe\u6837");

    model
         .description("\u8fd9\u662f\u8457\u540d\u7684\u53cc\u7f1d\u5e72\u6d89\u5b9e\u9a8c\u6a21\u578b\u3002\u901a\u8fc7\u5c4f\u5e55\u72ed\u7f1d\u5f15\u5165\u4e24\u4e2a\u72ed\u957f\u7684\u6ce2\u5bfc\uff0c\u8ba1\u7b97\u51fa\u73b0\u5728\u5c4f\u5e55\u53e6\u4e00\u8fb9\u7684\u884d\u5c04\u56fe\u6837\u3002");

    model.label("diffraction_patterns.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
