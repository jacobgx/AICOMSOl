/*
 * deep_excavation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:13 by COMSOL 6.3.0.290. */
public class deep_excavation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Soil");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A_struts", "15[cm^2]", "\u652f\u67f1\u6a2a\u622a\u9762\u79ef");
    model.param().set("Depth", "0[m]", "\u5f00\u6316\u6df1\u5ea6\uff08\u53c2\u6570\uff09");
    model.param().set("E_struts", "2e5[MPa]", "\u652f\u67f1\u6768\u6c0f\u6a21\u91cf");
    model.param().set("l_struts", "30[m]", "\u652f\u67f1\u957f\u5ea6");
    model.param().set("n_struts", "40", "\u652f\u67f1\u6570\u91cf");
    model.param().set("S_struts", "n_struts*(E_struts*A_struts/l_struts)", "\u652f\u67f1\u521a\u5ea6");
    model.param().set("Stage_1", "-4.8[m]", "\u7b2c\u4e00\u4e2a\u5f00\u6316\u6b65\uff0c\u7b2c\u4e00\u652f\u67f1");
    model.param().set("Stage_2", "-9.3[m]", "\u7b2c\u4e8c\u4e2a\u5f00\u6316\u6b65\uff0c\u7b2c\u4e8c\u652f\u67f1");
    model.param().set("Stage_3", "-14.35[m]", "\u7b2c\u4e09\u4e2a\u5f00\u6316\u6b65\uff0c\u7b2c\u4e09\u652f\u67f1");
    model.param().set("U_max", "25[mm]", "\u8bb8\u7528\u5899\u4f53\u53d8\u5f62");
    model.param().set("X_stress", "-24[kPa]", "\u539f\u4f4d\u5730\u5e94\u529b\uff0cxx \u5206\u91cf");
    model.param().set("Y_stress", "-35[kPa]", "\u539f\u4f4d\u5730\u5e94\u529b\uff0cyy \u5206\u91cf");
    model.param().set("Z_stress", "-24[kPa]", "\u539f\u4f4d\u5730\u5e94\u529b\uff0czz \u5206\u91cf");

    model.func().create("rm1", "Ramp");
    model.func("rm1").label("\u659c\u5761\uff1a\u652f\u67f1\u529b");
    model.func("rm1").set("funcname", "strut_force");
    model.func("rm1").set("location", "U_max");
    model.func("rm1").set("slope", "S_struts");
    model.func("rm1").set("baseline", "0[N]");
    model.func("rm1").set("smoothzonelocactive", true);
    model.func("rm1").set("smoothzoneloc", "0.1[mm]");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{90, 60});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -60});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{90, 20});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, -20});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{30, 30});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, -30});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{0.8, 30});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{30, -30});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r3", "r4");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("r3");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"30", "Stage_1+1"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"30", "Stage_1"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"30", "Stage_2+1"});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"30", "Stage_2"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new int[]{30, 0});
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"30", "Stage_3+1"});
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"30", "Stage_3"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ls1", "ls2", "ls3", "r4");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5899\u9694\u677f");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(20);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5899\u571f\u4f53");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(5, 6);

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().set(12);
    model.component("comp1").cpl("genext1").set("srcframe", "material");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"X", "Y"});
    model.component("comp1").cpl().create("genext2", "GeneralExtrusion");
    model.component("comp1").cpl("genext2").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext2").selection().named("sel1");
    model.component("comp1").cpl("genext2").set("srcframe", "material");
    model.component("comp1").cpl("genext2").set("dstmap", new String[]{"X", "Y"});

    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("matchToMohrCoulomb", "inscribe");
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1").selection().set(1, 2);
    model.component("comp1").physics("solid").feature("lemm1").create("exs1", "ExternalStress", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1").selection().set(1, 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("StressInputType", "InSituStress");
    model.component("comp1").physics("solid").feature("lemm1").feature("exs1")
         .set("sins", new String[]{"X_stress", "0", "0", "0", "Y_stress", "0", "0", "0", "Z_stress"});
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(9, 10);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "genext1(v)", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().named("sel2");
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "genext2(u)", 0);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(20);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"X_stress*(y>Depth)", "0", "0"});
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl2").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u652f\u67f1 1");
    model.component("comp1").physics("solid").feature("bndl2").selection().set(17);
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("force", new String[]{"strut_force(abs(u))*(Depth<Stage_1)", "0", "0"});
    model.component("comp1").physics("solid").create("bndl3", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl3").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u652f\u67f1 2");
    model.component("comp1").physics("solid").feature("bndl3").selection().set(15);
    model.component("comp1").physics("solid").feature("bndl3").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl3")
         .set("force", new String[]{"strut_force(abs(u))*(Depth<Stage_2)", "0", "0"});
    model.component("comp1").physics("solid").create("bndl4", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl4").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u652f\u67f1 3");
    model.component("comp1").physics("solid").feature("bndl4").selection().set(13);
    model.component("comp1").physics("solid").feature("bndl4").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl4")
         .set("force", new String[]{"strut_force(abs(u))*(Depth<Stage_3)", "0", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4\uff0c\u4e0a\u5c42");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"20[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1900"});
    model.component("comp1").material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("Soil").set("phis", new String[]{"35[deg]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u571f\u58e4\uff0c\u4e0b\u5c42");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"60[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1900"});
    model.component("comp1").material("mat2").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat2").propertyGroup("Soil").set("cohesion0", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("Soil").set("phis", new String[]{"35[deg]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u6321\u571f\u5899");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"30[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.15"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2400"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 60);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis3").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis3").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "A_struts", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat").setIndex("pname", "A_struts", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m^2", 0);
    model.study("std1").feature("stat").setIndex("pname", "Depth", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,-2,-26)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", 0.5);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 0.5);
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 14, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.disp");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 14, 0);
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
    model.result("pg2").label("\u5851\u6027\u533a");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg2").feature("surf1").set("bandcount", 2);
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5899\u6320\u5ea6");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u5899\u6320\u5ea6 (mm)");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6c34\u5e73\u4f4d\u79fb (mm)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6df1\u5ea6 (m)");
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().named("sel1");
    model.result("pg3").feature("lngr1").set("expr", "y");
    model.result("pg3").feature("lngr1").set("unit", "m");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "u");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendprefix", "\u6df1\u5ea6 = ");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u6c89\u964d");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u8868\u9762\u6c89\u964d");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u4e0e\u9694\u677f\u7684\u8ddd\u79bb (m)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5782\u76f4\u4f4d\u79fb (mm)");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(8);
    model.result("pg4").feature("lngr1").set("expr", "v");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendprefix", "\u6df1\u5ea6 = ");
    model.result("pg4").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("zmax", "80");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e09\u7ef4\u4f4d\u79fb");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u6df1\u57fa\u5751\u5f00\u6316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u571f\u6728\u5de5\u7a0b\u9886\u57df\u5e38\u89c1\u7684\u6df1\u57fa\u5751\u5f00\u6316\u8fdb\u884c\u5efa\u6a21\u4eff\u771f\uff0c\u5176\u4e2d\u4f7f\u7528\u8fb9\u754c\u8f7d\u8377\u6765\u6a21\u62df\u571f\u4f53\u5f00\u6316\u8fc7\u7a0b\u4e2d\u4f5c\u7528\u4e8e\u6321\u571f\u5899\u7684\u539f\u4f4d\u4fa7\u5411\u538b\u529b\uff0c\u5e76\u5047\u8bbe\u571f\u58e4\u5448\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u5851\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("deep_excavation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
