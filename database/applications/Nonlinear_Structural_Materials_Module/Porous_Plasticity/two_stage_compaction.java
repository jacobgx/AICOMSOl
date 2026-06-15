/*
 * two_stage_compaction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:37 by COMSOL 6.3.0.290. */
public class two_stage_compaction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Porous_Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("para", "0[1]", "\u53c2\u6570");
    model.param().set("disp", "0[mm]", "\u4f4d\u79fb\u53c2\u6570");
    model.param().set("H0", "20[mm]", "\u7c89\u672b\u6a21\u5177\u7684\u521d\u59cb\u9ad8\u5ea6");
    model.param().set("R0", "H0", "\u7c89\u672b\u6a21\u5177\u7684\u521d\u59cb\u534a\u5f84");
    model.param().set("sigmay0", "200[MPa]", "\u521d\u59cb\u5c48\u670d\u5e94\u529b");
    model.param().set("q1", "1.5", "Tvergaard \u6821\u6b63\u7cfb\u6570");
    model.param().set("q2", "1", "Tvergaard \u6821\u6b63\u7cfb\u6570");
    model.param().set("q3", "2.25", "Tvergaard \u6821\u6b63\u7cfb\u6570");
    model.param().set("F0", "0.28", "\u521d\u59cb\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param().set("Fc", "0.36", "\u4e34\u754c\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param().set("Ff", "0.4", "\u5931\u6548\u5b54\u9699\u4f53\u79ef\u5206\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u51b2\u538b\u529b");
    model.func("int1").set("funcname", "PunchForce");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 1, 1, 0);
    model.func("int1").setIndex("table", 300, 1, 1);
    model.func("int1").setIndex("table", 1.1, 2, 0);
    model.func("int1").setIndex("table", 0, 2, 1);
    model.func("int1").setIndex("table", 2, 3, 0);
    model.func("int1").setIndex("table", 300, 3, 1);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "kN", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "H0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"R0/4", "2.5*H0"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"R0", "-H0/4"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(5);
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set(6, 7);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Force1", "PunchForce(para)");
    model.component("comp1").variable("var1")
         .descr("Force1", "\u51b2\u538b\u529b\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.component("comp1").variable("var1").set("Force2", "intop1(-solid2.sz)");
    model.component("comp1").variable("var1")
         .descr("Force2", "\u51b2\u538b\u529b\uff0c\u5355\u9636\u6bb5\u538b\u5b9e");
    model.component("comp1").variable("var1").set("delta", "1-intop2(1)/(2*H0)");
    model.component("comp1").variable("var1").descr("delta", "\u8f74\u5411\u538b\u5b9e");

    model.component("comp1").physics("solid").feature("lemm1").create("popl1", "PorousPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("popl1").set("YieldFunction", "GTN");
    model.component("comp1").physics("solid").feature("lemm1").create("act1", "Activation", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").feature("act1")
         .set("activation_expression", "para>1");
    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("solid").feature("rd1").selection().set(3);
    model.component("comp1").physics("solid").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.05);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("force", new String[]{"0", "0", "-PunchForce(para)*(para<=1)"});
    model.component("comp1").physics("solid").feature().duplicate("bndl2", "bndl1");
    model.component("comp1").physics("solid").feature("bndl2").selection().set(5);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("force", new String[]{"0", "0", "-PunchForce(para)*(para>=1.1)"});
    model.component("comp1").physics("solid2").feature("lemm1").create("popl1", "PorousPlasticity", 2);
    model.component("comp1").physics("solid2").feature("lemm1").feature("popl1").set("YieldFunction", "GTN");
    model.component("comp1").physics("solid2").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("solid2").feature("rd1").selection().set(3);
    model.component("comp1").physics("solid2").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("solid2").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid2").feature("dcnt1").feature("fric1").set("mu_fric", 0.05);
    model.component("comp1").physics("solid2").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid2").feature("disp1").selection().set(2);
    model.component("comp1").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid2").feature().duplicate("disp2", "disp1");
    model.component("comp1").physics("solid2").feature("disp2").selection().set(5);
    model.component("comp1").physics("solid2").feature("disp2").setIndex("U0", "-disp", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PoroplasticModel", "PoroplasticModel", "Poroplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel")
         .set("sigmags", new String[]{"sigmay0"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("f0", new String[]{"F0"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("q1GTN", new String[]{"q1"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("q2GTN", new String[]{"q2"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("q3GTN", new String[]{"q3"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("fc", new String[]{"Fc"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("ff", new String[]{"Ff"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(10, 11);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", 1, 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", 1, 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1e-1,2)", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 21, 0);
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
    model.result("pg1").feature("surf1").create("filt1", "Filter");
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "solid.isactive");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 21, 0);
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
    model.result("pg2").feature("surf1").create("filt1", "Filter");
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "solid.isactive");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.epvolGp"});
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "solid.isactive");
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
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("\u5f53\u524d\u5b54\u6d1e\u4f53\u79ef\u5206\u6570 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.fcvGp"});
    model.result("pg4").feature("surf1").create("filt1", "Filter");
    model.result("pg4").feature("surf1").feature("filt1").set("expr", "solid.isactive");
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

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u5355\u9636\u6bb5\u538b\u5b9e");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", 1, 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", 1, 0);
    model.study("std2").feature("stat").setIndex("pname", "disp", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.1,6.2)", 0);
    model.study("std2").feature("stat").setIndex("punit", "mm", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 50);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 63, 0);
    model.result("pg5").label("\u5e94\u529b (solid2)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", "1");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u2", "w2"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solid2rev", "Revolve2D");
    model.result().dataset("dset2solid2rev").set("data", "dset2");
    model.result().dataset("dset2solid2rev").set("revangle", 225);
    model.result().dataset("dset2solid2rev").set("startangle", -90);
    model.result().dataset("dset2solid2rev").set("hasspacevars", true);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2solid2rev");
    model.result("pg6").setIndex("looplevel", 63, 0);
    model.result("pg6").label("\u5e94\u529b, 3D (solid2)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solid2rev").set("hasspacevars", true);
    model.result("pg6").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("descractive", true);
    model.result("pg6").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def").set("scale", "1");
    model.result("pg5").run();
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(3);
    model.result().dataset().duplicate("dset1solidrev1", "dset1solidrev");
    model.result().dataset("dset1solidrev1").set("data", "dset3");
    model.result().dataset().duplicate("dset4", "dset2");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(3);
    model.result().dataset().duplicate("dset1solidrev2", "dset1solidrev1");
    model.result().dataset("dset1solidrev2").set("data", "dset4");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "dataset");
    model.nodeGroup("grp1").placeAfter("dataset", "dset2solid2rev");
    model.nodeGroup("grp1").add("dataset", "dset3");
    model.nodeGroup("grp1").add("dataset", "dset1solidrev1");
    model.nodeGroup("grp1").add("dataset", "dset4");
    model.nodeGroup("grp1").add("dataset", "dset1solidrev2");
    model.nodeGroup("grp1").label("\u6a21\u5177\u6570\u636e\u96c6");

    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature().remove("sel1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset3");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg5").run();
    model.result().move("pg5", 1);
    model.result("pg5").label("\u5e94\u529b\uff0c\u5355\u9636\u6bb5\u538b\u5b9e");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature().remove("sel1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").set("data", "dset4");
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u5bf9\u5bc6\u5ea6\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.rhorelGp");
    model.result("pg2").feature("surf1").set("descr", "\u5f53\u524d\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "dset1solidrev1");
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg6").run();
    model.result().move("pg6", 3);
    model.result("pg6").label("\u76f8\u5bf9\u5bc6\u5ea6\uff0c\u5355\u9636\u6bb5\u538b\u5b9e");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "solid2.rhorelGp");
    model.result("pg6").feature("surf1").set("descr", "\u5f53\u524d\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "dset1solidrev2");
    model.result("pg6").feature("surf2").set("solutionparams", "parent");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature().remove("filt1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "dset2");
    model.result("pg3").feature("surf2").set("expr", "if(isnan(solid2.epvol),NaN,solid2.epvol)");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("trn1").set("move", new String[]{"0", "50[mm]"});
    model.result("pg3").run();
    model.result("pg3").create("tlan1", "TableAnnotation");
    model.result("pg3").feature("tlan1").set("source", "localtable");
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.025, 0, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.02, 0, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\u4e24\u9636\u6bb5\u538b\u5b9e", 0, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.025, 1, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.07, 1, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\u5355\u9636\u6bb5\u538b\u5b9e", 1, 2);
    model.result("pg3").feature("tlan1").set("showpoint", false);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u5b54\u6d1e\u4f53\u79ef\u5206\u6570");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature().remove("def");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature().remove("filt1");
    model.result("pg4").feature("surf2").feature().remove("def");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset2");
    model.result("pg4").feature("surf2").set("expr", "if(isnan(solid2.fcv),NaN,solid2.fcv)");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").set("inheritplot", "surf1");
    model.result("pg4").feature("surf2").create("trn1", "Transformation");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("trn1").set("move", new String[]{"0", "50[mm]"});
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.025, 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.02, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u4e24\u9636\u6bb5\u538b\u5b9e", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.025, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.07, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u5355\u9636\u6bb5\u538b\u5b9e", 1, 2);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u5e73\u5747\u5f39\u6027\u4f53\u79ef\u5e94\u53d8\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.result().evaluationGroup("eg1").create("av1", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av1").selection().set(1);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "para", 0);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "solid.eelvol*solid.isactive", 1);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u5f39\u6027\u4f53\u79ef\u5e94\u53d8", 1);
    model.result().evaluationGroup("eg1").create("av2", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av2").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av2").selection().set(2);
    model.result().evaluationGroup("eg1").feature("av2").setIndex("expr", "solid.eelvol*solid.isactive", 0);
    model.result().evaluationGroup("eg1").feature("av2")
         .setIndex("descr", "\u5f39\u6027\u4f53\u79ef\u5e94\u53d8", 0);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7")
         .label("\u5e73\u5747\u5f39\u6027\u4f53\u79ef\u5e94\u53d8\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg7").feature("tblp1").set("xaxisdata", 1);
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "\u7b2c\u4e00\u4e2a\u7c89\u672b\u6a21\u5177", 0);
    model.result("pg7").feature("tblp1").setIndex("legends", "\u7b2c\u4e8c\u4e2a\u7c89\u672b\u6a21\u5177", 1);
    model.result("pg7").run();
    model.result("pg7").create("ann1", "Annotation");
    model.result("pg7").feature("ann1").set("text", "\u5f39\u6027\u56de\u5f39");
    model.result("pg7").feature("ann1").set("posxexpr", 1.05);
    model.result("pg7").feature("ann1").set("posyexpr", -0.0015);
    model.result("pg7").feature("ann1").set("showpoint", false);
    model.result("pg7").feature("ann1").set("showframe", true);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u51b2\u538b\u529b\u4e0e\u8f74\u5411\u538b\u5b9e\u7684\u5173\u7cfb");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u51b2\u538b\u529b (kN)");
    model.result("pg8").set("xlabel", "\u8f74\u5411\u538b\u5b9e");
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", -0.003);
    model.result("pg8").set("xmax", 0.16);
    model.result("pg8").set("ymin", -10);
    model.result("pg8").set("ymax", 320);
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "Force1", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "delta");
    model.result("pg8").feature("glob1").set("legendmethod", "manual");
    model.result("pg8").feature("glob1").setIndex("legends", "\u4e24\u9636\u6bb5\u538b\u5b9e", 0);
    model.result("pg8").feature().duplicate("glob2", "glob1");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("data", "dset2");
    model.result("pg8").feature("glob2").setIndex("expr", "Force2", 0);
    model.result("pg8").feature("glob2").setIndex("legends", "\u5355\u9636\u6bb5\u538b\u5b9e", 0);
    model.result("pg8").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u76f8\u5bf9\u5bc6\u5ea6\uff0c\u4e24\u9636\u6bb5\u538b\u5b9e");
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("frametime", 0.5);
    model.result().export("anim1").set("synchronize", false);
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 21);
    model.result().export("anim1").set("showframe", 21);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u76f8\u5bf9\u5bc6\u5ea6\uff0c\u5355\u9636\u6bb5\u538b\u5b9e");
    model.result().export("anim2").set("plotgroup", "pg6");
    model.result().export("anim2").showFrame();
    model.result("pg2").run();

    model.title("\u4e24\u9636\u6bb5\u7c89\u672b\u538b\u5236\u5de5\u827a");

    model
         .description("\u7c89\u672b\u538b\u5236\u662f\u7c89\u672b\u51b6\u91d1\u4e2d\u7684\u4e00\u4e2a\u5173\u952e\u5de5\u827a\uff0c\u4e3a\u70e7\u7ed3\u751f\u4ea7\u51fa\u5177\u6709\u590d\u6742\u5f62\u72b6\u7684\u9ad8\u8d28\u91cf\u4ea7\u54c1\u63d0\u4f9b\u4e86\u7075\u6d3b\u6027\u3002\u538b\u576f\u5bc6\u5ea6\u662f\u51b3\u5b9a\u70e7\u7ed3\u4ea7\u54c1\u6574\u4f53\u8d28\u91cf\u7684\u5173\u952e\u56e0\u7d20\uff0c\u539f\u56e0\u662f\u5bc6\u5ea6\u8f83\u4f4e\u7684\u533a\u57df\u4f1a\u964d\u4f4e\u5176\u673a\u68b0\u5f3a\u5ea6\u3002\n\n\u591a\u7ea7\u538b\u5236\u5de5\u827a\u662f\u8ba9\u5de5\u4ef6\u5b9e\u73b0\u5747\u5300\u5bc6\u5ea6\u7684\u4e00\u79cd\u65b9\u6cd5\u3002\u672c\u4f8b\u901a\u8fc7\u4e00\u4e2a\u7b80\u5355\u7684\u8bbe\u7f6e\u6f14\u793a\u4e24\u9636\u6bb5\u538b\u5236\u8fc7\u7a0b\uff0c\u5e76\u5c06\u5206\u6790\u7ed3\u679c\u4e0e\u5355\u9636\u6bb5\u8fc7\u7a0b\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("two_stage_compaction.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
