/*
 * squeeze_film_accelerometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:05 by COMSOL 6.3.0.290. */
public class squeeze_film_accelerometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("tff", "ThinFilmFlowShell", "geom1");
    model.component("comp1").physics("tff").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp1").physics("tff").feature("ffp1")
         .set("TangentialWallVelocity", new String[]{"FromDeformation"});
    model.component("comp1").physics("tff").feature("ffp1")
         .set("TangentialBaseVelocity", new String[]{"FromDeformation"});

    model.component("comp1").multiphysics().create("stfi1", "StructureThinFilmFlowInteraction", 2);
    model.component("comp1").multiphysics("stfi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("stfi1").set("Thinfilm_physics", "tff");
    model.component("comp1").multiphysics("stfi1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/tff", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/stfi1", true);

    model.param().label("\u6d41\u4f53\u5c5e\u6027\u4e0e\u8f7d\u8377");
    model.param().set("a", "g_const/2");
    model.param().descr("a", "\u5916\u52a0\u7684\u52a0\u901f\u5ea6");
    model.param().set("mu", "22e-6[Pa*s]");
    model.param().descr("mu", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6db2\u819c");
    model.param().set("pA", "300[Pa]");
    model.param().descr("pA", "\u73af\u5883\u6c14\u538b");
    model.param().set("h0", "3.95[um]");
    model.param().descr("h0", "\u521d\u59cb\u819c\u539a");
    model.param().set("Lambda0", "70[nm]");
    model.param().descr("Lambda0", "\u5e73\u5747\u81ea\u7531\u7a0b");
    model.param().set("pref", "1[atm]");
    model.param().descr("pref", "\u53c2\u8003\u538b\u529b");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55");
    model.param("par2").set("Lpm", "1780[um]");
    model.param("par2").descr("Lpm", "\u8d28\u91cf\u5757\u957f\u5ea6");
    model.param("par2").set("Hpm", "400[um]");
    model.param("par2").descr("Hpm", "\u8d28\u91cf\u5757\u9ad8\u5ea6");
    model.param("par2").set("Wpm", "2960[um]");
    model.param("par2").descr("Wpm", "\u8d28\u91cf\u5757\u5bbd\u5ea6");
    model.param("par2").set("Lc", "520[um]");
    model.param("par2").descr("Lc", "\u60ac\u81c2\u957f\u5ea6");
    model.param("par2").set("Hc", "40[um]");
    model.param("par2").descr("Hc", "\u60ac\u81c2\u9ad8\u5ea6");
    model.param("par2").set("Wc", "100[um]");
    model.param("par2").descr("Wc", "\u60ac\u81c2\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u8d28\u91cf\u5757");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lpm", "Wpm", "Hpm"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u60ac\u81c2 1");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"Lc", "Wc", "Hc"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-Lc", "2*Wc", "(Hpm - Hc)/2"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u60ac\u81c2 2");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"Lc", "Wc", "Hc"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-Lc", "Wpm - 3*Wc", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "(Hpm - Hc)/2", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});

    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "0", "-a*solid.rho"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 6);
    model.component("comp1").physics("tff").selection().set(13, 14);
    model.component("comp1").physics("tff").prop("ReferencePressure").set("pref", "pref");
    model.component("comp1").physics("tff").feature("ffp1").set("editModelInputs", true);
    model.component("comp1").physics("tff").feature("ffp1").set("minput_pressure", "pA");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "h0");
    model.component("comp1").physics("tff").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("mure", "mu");
    model.component("comp1").physics("tff").feature("ffp1").set("QchTypeMR", "Model1");
    model.component("comp1").physics("tff").feature("ffp1")
         .set("MeanFreePathRarefied", "UserDefinedReferencePressure");
    model.component("comp1").physics("tff").feature("ffp1").set("lambda0", "Lambda0");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u5e95\u9762\u79ef\u5206\u7b97\u5b50");
    model.component("comp1").cpl("intop1").set("opname", "bf");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(13);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u9876\u9762\u79ef\u5206\u7b97\u5b50");
    model.component("comp1").cpl("intop2").set("opname", "tf");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(14);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u603b\u529b");
    model.component("comp1").variable("var1").set("F_bottom", "-bf(nz*pfilm)");
    model.component("comp1").variable("var1").descr("F_bottom", "\u603b\u529b\uff0c\u5e95\u9762");
    model.component("comp1").variable("var1").set("F_top", "-tf(nz*pfilm)");
    model.component("comp1").variable("var1").descr("F_top", "\u603b\u529b\uff0c\u9876\u9762");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/solid2", true);

    model.component("comp2").physics().create("tff2", "ThinFilmFlowEdge", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/tff2", true);

    model.component("comp2").physics("tff2").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp2").physics("tff2").feature("ffp1")
         .set("TangentialWallVelocity", new String[]{"FromDeformation"});
    model.component("comp2").physics("tff2").feature("ffp1")
         .set("TangentialBaseVelocity", new String[]{"FromDeformation"});

    model.component("comp2").multiphysics().create("stfi2", "StructureThinFilmFlowInteraction", 1);

    model.study("std1").feature("time").setSolveFor("/multiphysics/stfi2", true);

    model.component("comp2").multiphysics("stfi2").set("Structure_physics", "solid2");
    model.component("comp2").multiphysics("stfi2").set("Thinfilm_physics", "tff2");
    model.component("comp2").multiphysics("stfi2").selection().all();

    model.component("comp2").geom("geom2").run();
    model.component("comp2").geom("geom2").lengthUnit("\u00b5m");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").label("\u8d28\u91cf\u5757");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"Lpm", "Hpm"});
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("r2").label("\u60ac\u81c2");
    model.component("comp2").geom("geom2").feature("r2").set("size", new String[]{"Lc", "Hc"});
    model.component("comp2").geom("geom2").feature("r2").set("pos", new String[]{"-Lc", "(Hpm-Hc)/2"});
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat2").label("Silicon");
    model.component("comp2").material("mat2").set("family", "custom");
    model.component("comp2").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp2").material("mat2").set("diffuse", "custom");
    model.component("comp2").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp2").material("mat2").set("ambient", "custom");
    model.component("comp2").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp2").material("mat2").set("noise", true);
    model.component("comp2").material("mat2").set("fresnel", 0.7);
    model.component("comp2").material("mat2").set("roughness", 0.5);
    model.component("comp2").material("mat2").set("diffusewrap", 0);
    model.component("comp2").material("mat2").set("reflectance", 0);
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});

    model.component("comp2").physics("solid2").create("ct1", "ChangeThickness", 2);
    model.component("comp2").physics("solid2").feature("ct1").label("\u60ac\u81c2\u539a\u5ea6");
    model.component("comp2").physics("solid2").feature("ct1").selection().set(1);
    model.component("comp2").physics("solid2").feature("ct1").set("d", "2*Wc");
    model.component("comp2").physics("solid2").create("ct2", "ChangeThickness", 2);
    model.component("comp2").physics("solid2").feature("ct2").label("\u8d28\u91cf\u5757\u539a\u5ea6");
    model.component("comp2").physics("solid2").feature("ct2").selection().set(2);
    model.component("comp2").physics("solid2").feature("ct2").set("d", "Wpm");
    model.component("comp2").physics("solid2").create("bl1", "BodyLoad", 2);
    model.component("comp2").physics("solid2").feature("bl1").selection().set(1, 2);
    model.component("comp2").physics("solid2").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "-a*solid2.rho", "0"});
    model.component("comp2").physics("solid2").create("fix1", "Fixed", 1);
    model.component("comp2").physics("solid2").feature("fix1").selection().set(1);
    model.component("comp2").physics("tff2").selection().set(5, 8);
    model.component("comp2").physics("tff2").prop("ReferencePressure").set("pref", "pref");
    model.component("comp2").physics("tff2").feature("ffp1").set("editModelInputs", true);
    model.component("comp2").physics("tff2").feature("ffp1").set("minput_pressure", "pA");
    model.component("comp2").physics("tff2").feature("ffp1").set("hw1", "h0");
    model.component("comp2").physics("tff2").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp2").physics("tff2").feature("ffp1").set("mure", "mu");
    model.component("comp2").physics("tff2").feature("ffp1").set("QchTypeMR", "Model1");
    model.component("comp2").physics("tff2").feature("ffp1")
         .set("MeanFreePathRarefied", "UserDefinedReferencePressure");
    model.component("comp2").physics("tff2").feature("ffp1").set("lambda0", "Lambda0");

    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").label("\u5e95\u9762\u79ef\u5206\u7b97\u5b50");
    model.component("comp2").cpl("intop3").set("opname", "bf2d");
    model.component("comp2").cpl("intop3").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop3").selection().set(5);
    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").label("\u9876\u9762\u79ef\u5206\u7b97\u5b50");
    model.component("comp2").cpl("intop4").set("opname", "tf2d");
    model.component("comp2").cpl("intop4").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop4").selection().set(8);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").label("\u53d8\u91cf\uff1a\u603b\u529b");
    model.component("comp2").variable("var2").set("F_bottom2d", "-bf2d(ny*pfilm2*solid2.d)");
    model.component("comp2").variable("var2").descr("F_bottom2d", "\u603b\u963b\u5c3c\u529b\uff0c\u5e95\u9762");
    model.component("comp2").variable("var2").set("F_top2d", "-tf2d(ny*pfilm2*solid2.d)");
    model.component("comp2").variable("var2").descr("F_top2d", "\u603b\u963b\u5c3c\u529b\uff0c\u9876\u9762");

    model.component("comp2").mesh("mesh2").create("fq1", "FreeQuad");

    model.component("comp2").multiphysics("stfi2").selection().set(5, 8);

    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", 85.1);
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", 0.288);
    model.component("comp2").mesh("mesh2").feature("size").set("hgrad", 1.25);
    model.component("comp2").mesh("mesh2").feature("size").set("hcurve", 0.25);
    model.component("comp2").mesh("mesh2").feature("size").set("hnarrow", 2);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s^2", 0);
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s^2", 0);
    model.study("std1").feature("param").setIndex("pname", "pA", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1000 300 50", 0);
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(0,4e-2,4)");
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (tff)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "pfilm");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").label("\u5e94\u529b (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6d41\u4f53\u538b\u529b (tff2)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "pfilm2");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("line1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").label("\u4f4d\u79fb (solid2)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid2.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("resolution", "normal");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").label("\u4f4d\u79fb (solid2)");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\u548c\u6d41\u4f53\u8f7d\u8377 (2D)");
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"tff2.fwallx", "v2"});
    model.result("pg5").feature("arwl1").setIndex("expr", "tff2.fwally", 1);
    model.result("pg5").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg5").feature("arwl1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5e95\u9762\u603b\u529b (3D)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4 (ms)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "F_bottom", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "uN", 0);
    model.result("pg6").feature("glob1").set("autodescr", false);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5782\u76f4\u4f4d\u79fb (3D)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u65f6\u95f4 (ms)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u4f4d\u79fb\uff0cz \u5206\u91cf (um)");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(21);
    model.result("pg7").feature("ptgr1").set("expr", "w");
    model.result("pg7").feature("ptgr1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg7").feature("ptgr1").set("autopoint", false);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg5").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5e95\u9762\u603b\u529b (2D)");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u65f6\u95f4 (ms)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u603b\u529b\uff0c\u5e95\u9762 (uN)");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "F_bottom2d", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "uN", 0);
    model.result("pg8").feature("glob1").set("autodescr", false);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u5782\u76f4\u4f4d\u79fb (2D)");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u65f6\u95f4 (ms)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u4f4d\u79fb\uff0cz \u5206\u91cf (um)");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").selection().set(7);
    model.result("pg9").feature("ptgr1").set("expr", "v2");
    model.result("pg9").feature("ptgr1").set("descr", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr1").set("autopoint", false);
    model.result("pg9").feature("ptgr1").set("legend", true);

    model.title("\u52a0\u901f\u5ea6\u8ba1\u4e2d\u7684\u538b\u819c\u6c14\u4f53\u963b\u5c3c");

    model
         .description("\u7535\u5bb9\u4f20\u611f\u5fae\u673a\u68b0\u7ed3\u6784\u901a\u5e38\u5728\u7535\u6781\u4e4b\u95f4\u5177\u6709\u975e\u5e38\u7a84\u7684\u95f4\u9699\u3002\u95f4\u9699\u4e2d\u5e38\u5e38\u5145\u6ee1\u6c14\u4f53\uff0c\u4ee5\u5bf9\u673a\u68b0\u96f6\u4ef6\u7684\u8fd0\u52a8\u4ea7\u751f\u963b\u5c3c\u4f5c\u7528\u3002\u8fd9\u662f\u4e00\u4e2a\u4e09\u7ef4\u548c\u4e8c\u7ef4\u7ec4\u5408\u6a21\u578b\uff0c\u6f14\u793a\u5982\u4f55\u5728\u5fae\u7cfb\u7edf\u52a0\u901f\u5ea6\u8ba1\u4e2d\u5c06\u538b\u819c\u6c14\u4f53\u963b\u5c3c\u4e0e\u673a\u68b0\u4f4d\u79fb\u8fdb\u884c\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("squeeze_film_accelerometer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
