/*
 * elastoplastic_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:34 by COMSOL 6.3.0.290. */
public class elastoplastic_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{18, 10});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");

    model.param().set("para", "0");
    model.param().descr("para", "\u6c34\u5e73\u8f7d\u8377\u53c2\u6570");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "loadfunc");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 1.1, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 133.65, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 2.2, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 2, 1);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", "MPa", 0);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "10[mm]");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").label("\u7ebf\u6027\u786c\u5316");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"70[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"243[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Et", new String[]{"2.171[GPa]"});

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"loadfunc(para)", "0", "0"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("ref1", "Refine");
    model.component("comp1").mesh("mesh1").feature("ref1").set("boxcoord", true);
    model.component("comp1").mesh("mesh1").feature("ref1").set("xmax", 8);
    model.component("comp1").mesh("mesh1").feature("ref1").set("ymax", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 range(0.40,0.05,2.2)", 0);
    model.study("std1").label("\u7ebf\u6027\u786c\u5316");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 38, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u7ebf\u6027\u786c\u5316");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 38, 0);
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u5851\u6027\u533a\u57df\uff0c\u7ebf\u6027\u786c\u5316");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 7, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 15, 0);
    model.result("pg2").run();

    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "stress_strain_curve");
    model.component("comp1").func("int2").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int2").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int2").setIndex("table", "243e6/70e9", 1, 0);
    model.component("comp1").func("int2").setIndex("table", 243, 1, 1);
    model.component("comp1").func("int2").setIndex("table", "243e6/70e9+50e6/2.171e9", 2, 0);
    model.component("comp1").func("int2").setIndex("table", "243+50", 2, 1);
    model.component("comp1").func("int2").setIndex("argunit", 1, 0);
    model.component("comp1").func("int2").setIndex("fununit", "MPa", 0);
    model.component("comp1").func("int2").set("extrap", "linear");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("hardening", "max(0,stress_strain_curve(solid.epe+solid.mises/solid.E)-solid.sigmags)");
    model.component("comp1").variable("var1").descr("hardening", "\u786c\u5316\u51fd\u6570");

    model.component("comp1").physics("solid").feature("lemm1").create("plsty2", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .label("\u5185\u63d2\u786c\u5316\u548c\u7528\u6237\u5b9a\u4e49\u7684\u5851\u6027\u6d41\u52a8");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("YieldFunction", "userDefined");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("sigmagf", "sqrt(3*solid.II2sEff)");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2").set("FlowPotential", "userDefined");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("Qplast", "sqrt(3*solid.II2sEff+eps)");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("IsotropicHardeningModel", "HardeningFunction");

    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"hardening"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "0 range(0.40,0.05,2.2)", 0);
    model.study("std2")
         .label("\u5185\u63d2\u786c\u5316\u548c\u7528\u6237\u5b9a\u4e49\u7684\u5851\u6027\u6d41\u52a8");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u5e94\u529b\u3001\u5185\u63d2\u786c\u5316\u548c\u7528\u6237\u5b9a\u4e49\u7684\u5851\u6027\u6d41\u52a8");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u5851\u6027\u533a\u57df\u3001\u5185\u63d2\u786c\u5316\u548c\u7528\u6237\u5b9a\u4e49\u7684\u5851\u6027\u6d41\u52a8");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/plsty2"});

    model.title("\u7a7f\u5b54\u677f\u7684\u5f39\u5851\u6027\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u52a0\u8f7d\u5230\u5851\u6027\u72b6\u6001\u7684\u7a7f\u5b54\u677f\u8fdb\u884c\u5206\u6790\uff0c\u5176\u4e2d\u4e00\u90e8\u5206\u662f\u57fa\u51c6\u5206\u6790\uff0c\u6b64\u5916\uff0c\u8fd8\u7814\u7a76\u4e86\u677f\u7684\u5378\u8f7d\u8fc7\u7a0b\u548c\u6b8b\u4f59\u5e94\u529b\u3002\n\n\u672c\u4f8b\u7684\u53e6\u4e00\u90e8\u5206\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u57fa\u4e8e\u6d4b\u91cf\u7684\u5e94\u529b-\u5e94\u53d8\u66f2\u7ebf\u7684\u786c\u5316\u51fd\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("elastoplastic_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
