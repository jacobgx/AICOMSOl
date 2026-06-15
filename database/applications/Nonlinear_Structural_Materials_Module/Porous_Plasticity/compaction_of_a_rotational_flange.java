/*
 * compaction_of_a_rotational_flange.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:36 by COMSOL 6.3.0.290. */
public class compaction_of_a_rotational_flange {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Porous_Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("t", "0[s]");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u9876\u90e8\u51b2\u5934\u4f4d\u79fb");
    model.component("comp1").func("int1").set("funcname", "disp1");
    model.component("comp1").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 0, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 10, 1, 0);
    model.component("comp1").func("int1").setIndex("table", -6.06, 1, 1);
    model.component("comp1").func("int1").setIndex("argunit", "s", 0);
    model.component("comp1").func("int1").setIndex("fununit", "mm", 0);
    model.component("comp1").func().duplicate("int2", "int1");
    model.component("comp1").func("int2").label("\u5e95\u90e8\u51b2\u5934\u4f4d\u79fb");
    model.component("comp1").func("int2").setIndex("table", 7.7, 1, 1);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{6.3, 25.4});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{4.6, 13.7});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{6.3, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{20.2, 11.7});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{6.3, 13.7});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{15.6, 13.7});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{10.9, 0});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{6.3, 25.4});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{26.5, 0});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("r4", "r5");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u5185\u90e8\u6a21\u5177");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("r2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("r3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u538b\u7f29");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("r4");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("r5");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("uni2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").label("\u5916\u90e8\u6a21\u5177");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 8);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").pair("ap2").swap();

    model.component("comp1").physics("solid").selection().set(2);
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "totalLagrangian");
    model.component("comp1").physics("solid").feature("lemm1").set("strainDecomposition", "multiplicative");
    model.component("comp1").physics("solid").feature("lemm1").create("popl1", "PorousPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("popl1")
         .set("YieldFunction", "DruckerPrager");
    model.component("comp1").physics("solid").feature("lemm1").feature("popl1")
         .set("capHardeningModel", "exponential");
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt1").set("SolutionMethod", "coupled");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.08);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(7);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "disp1(t)", 2);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().set(6);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "disp2(t)", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94c1\u7c89\u672b");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"2[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.37"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7540"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PoroplasticModel", "PoroplasticModel", "Poroplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel")
         .set("sigmags", new String[]{"43.5[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("f0", new String[]{"0.6"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PressureDependentPlasticity", "PressureDependentPlasticity", "Pressure_dependent_plasticity");
    model.component("comp1").material("mat1").propertyGroup("PressureDependentPlasticity")
         .set("a1yield", new String[]{"1.3"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("CapAndCutoff", "CapAndCutoff", "Cap_and_cutoff");
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("pc0", new String[]{"10[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("pcc0", new String[]{"1.25[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("Kc", new String[]{"100[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("epvolmax", new String[]{"0.65"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(6, 19);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map2").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(7, 18);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.2,10)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "0.0005");
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 0.2);
    model.sol("sol1").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epvolGp"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").label("\u5f53\u524d\u5b54\u6d1e\u4f53\u79ef\u5206\u6570 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.fcvGp"});
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", "1");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "Traffic");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u5f53\u524d\u5b54\u6d1e\u4f53\u79ef\u5206\u6570");
    model.result("pg4").label("\u5f53\u524d\u5b54\u6d1e\u4f53\u79ef\u5206\u6570 (solid)");
    model.result("pg4").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2);
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 3);
    model.result().dataset().duplicate("dset1solidrev1", "dset1solidrev");
    model.result().dataset("dset1solidrev1").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "dset1solidrev1");
    model.result("pg2").feature("surf2").set("expr", "0");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg2").run();

    model.view("view2").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").set("legendactive", true);
    model.result("pg3").set("legendprecision", 4);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("bandcount", 6);
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u538b\u5236\u8fc7\u7a0b\u4e2d\u7684\u5f53\u524d\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg4").setIndex("looplevel", 26, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid.rhorelGp");
    model.result("pg4").feature("surf1").set("descractive", false);
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u538b\u5236\u7ed3\u675f\u65f6\u7684\u5f53\u524d\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u65cb\u8f6c\u6cd5\u5170\u7ec4\u4ef6\u7684\u7c89\u672b\u538b\u5236");

    model
         .description("\u7531\u4e8e\u7c89\u672b\u538b\u5236\u5de5\u827a\u5177\u6709\u751f\u4ea7\u5f62\u72b6\u590d\u6742\u7684\u9ad8\u5f3a\u5ea6\u7ec4\u4ef6\u7684\u6f5c\u529b\uff0c\u56e0\u6b64\u5728\u5236\u9020\u4e1a\u4e2d\u7684\u5e94\u7528\u8d8a\u6765\u8d8a\u666e\u904d\u3002\u672c\u4f8b\u4f7f\u7528\u5e26\u5e3d\u7684\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u5851\u6027\u6a21\u578b\u6765\u5206\u6790\u94c1\u7c89\u538b\u5236\u4ee5\u5f62\u6210\u8f74\u5bf9\u79f0\u65cb\u8f6c\u6cd5\u5170\u7ec4\u4ef6\u7684\u8fc7\u7a0b\uff0c\u5176\u4e2d\u8003\u8651\u4e86\u91d1\u5c5e\u7c89\u672b\u4e0e\u6a21\u5177\u4e4b\u95f4\u7684\u6469\u64e6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("compaction_of_a_rotational_flange.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
