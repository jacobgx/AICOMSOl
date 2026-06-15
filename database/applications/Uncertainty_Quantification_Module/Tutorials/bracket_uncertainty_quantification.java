/*
 * bracket_uncertainty_quantification.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:33 by COMSOL 6.3.0.290. */
public class bracket_uncertainty_quantification {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Uncertainty_Quantification_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1")
         .insertFile("bracket_uncertainty_quantification_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().create("par2");
    model.param("par2").set("P0", "2.5[MPa]");
    model.param("par2").descr("P0", "\u5355\u4f4d\u9762\u79ef\u7684\u529b");
    model.param("par2").set("dx", "lp+ts");
    model.param("par2").descr("dx", "\u9500\u5b54\u7684 X \u95f4\u8ddd");
    model.param("par2").set("hmax", "ts/1.5");
    model.param("par2").descr("hmax", "\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param("par2").set("hmin", "ts/2.5");
    model.param("par2").descr("hmin", "\u6700\u5c0f\u5355\u5143\u5927\u5c0f");
    model.param("par2").set("YC", "-ls+r2");
    model.param("par2").descr("YC", "\u5b54\u4e2d\u5fc3\u7684 Y \u5750\u6807");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(20, 21, 22, 23, 24, 25, 26, 27, 34, 35, 36, 37, 38, 39, 40, 41);
    model.component("comp1").selection("sel1").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").add(4, 5, 7, 8);
    model.component("comp1").selection("sel2").label("\u5de6\u9500\u5b54");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").add(45, 46, 48, 49);
    model.component("comp1").selection("sel3").label("\u53f3\u9500\u5b54");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection("uni1").label("\u9500\u5b54");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel2");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("sel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("z1", "aveop1(w)");
    model.component("comp1").variable("var1").descr("z1", "\u5de6\u9500\u5b54\u4e2d\u5fc3");
    model.component("comp1").variable("var1").set("z2", "aveop2(w)");
    model.component("comp1").variable("var1").descr("z2", "\u53f3\u9500\u5b54\u4e2d\u5fc3");
    model.component("comp1").variable("var1").set("phi", "atan((z2-z1)/dx)[1/deg]");
    model.component("comp1").variable("var1").descr("phi", "\u504f\u5dee\u89d2");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F, py, px");
    model.component("comp1").func("an1").set("fununit", "Pa");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "hmin");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel1");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load(-P0,Y-YC,Z)*(sign(X)*Z>0)"});
    model.component("comp1").physics("solid").feature("bndl1").selection().named("uni1");

    model.study("std1").label("\u7814\u7a76 1\uff0c\u9759\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 100);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("ref", "StudyReference");
    model.study("std2").feature("ref").set("studyref", "std1");
    model.study("std2").create("uq", "UncertaintyQuantification");
    model.study("std2").label("\u7814\u7a76 2\uff0c\u7b5b\u9009");
    model.study("std2").feature("uq").setIndex("funcname", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std2").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std2").feature("uq").setIndex("failif", "larger", 0);
    model.study("std2").feature("uq").setIndex("threshold", "", 0);
    model.study("std2").feature("uq").setIndex("funcname", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std2").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std2").feature("uq").setIndex("failif", "larger", 0);
    model.study("std2").feature("uq").setIndex("threshold", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "comp1.phi", 0);
    model.study("std2").feature("uq").setIndex("pname", "cd", 0);
    model.study("std2").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 0);
    model.study("std2").feature("uq").setIndex("pname", "cd", 0);
    model.study("std2").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 0);
    model.study("std2").feature("uq").setIndex("pname", "da_r3", 1);
    model.study("std2").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 1);
    model.study("std2").feature("uq").setIndex("pname", "da_r3", 1);
    model.study("std2").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 1);
    model.study("std2").feature("uq").setIndex("pname", "db_r3", 2);
    model.study("std2").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 2);
    model.study("std2").feature("uq").setIndex("pname", "db_r3", 2);
    model.study("std2").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 2);
    model.study("std2").feature("uq").setIndex("pname", "dx", 3);
    model.study("std2").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 3);
    model.study("std2").feature("uq").setIndex("pname", "dx", 3);
    model.study("std2").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 3);
    model.study("std2").feature("uq").setIndex("pname", "hf", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 4);
    model.study("std2").feature("uq").setIndex("pname", "hf", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 4);
    model.study("std2").feature("uq").setIndex("pname", "hm", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 5);
    model.study("std2").feature("uq").setIndex("pname", "hm", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 5);
    model.study("std2").feature("uq").setIndex("pname", "hmax", 6);
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "hmax", 6);
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "ts", 0);
    model.study("std2").feature("uq").setIndex("pname", "lp", 1);
    model.study("std2").feature("uq").setIndex("pname", "ls", 2);
    model.study("std2").feature("uq").setIndex("pname", "hm", 3);
    model.study("std2").feature("uq").setIndex("pname", "wp", 4);
    model.study("std2").feature("uq").setIndex("pname", "wf", 5);
    model.study("std2").feature("uq").setIndex("pname", "r1", 6);
    model.study("std2").feature("uq").setEntry("distributionselection", "col1", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col1", "ts");
    model.study("std2").feature("uq").setEntry("s2selection", "col1", "0.01*ts");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col1", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col1", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col1", "0.99*ts");
    model.study("std2").feature("uq").setEntry("uboundselection", "col1", "1.01*ts");
    model.study("std2").feature("uq").setEntry("distributionselection", "col2", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col2", "lp");
    model.study("std2").feature("uq").setEntry("s2selection", "col2", "0.01*lp");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col2", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col2", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col2", "0.95*lp");
    model.study("std2").feature("uq").setEntry("uboundselection", "col2", "1.05*lp");
    model.study("std2").feature("uq").setEntry("distributionselection", "col3", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col3", "ls");
    model.study("std2").feature("uq").setEntry("s2selection", "col3", "0.01*ls");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col3", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col3", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col3", "0.95*ls");
    model.study("std2").feature("uq").setEntry("uboundselection", "col3", "1.05*ls");
    model.study("std2").feature("uq").setEntry("distributionselection", "col4", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col4", "hm");
    model.study("std2").feature("uq").setEntry("s2selection", "col4", "0.01*hm");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col4", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col4", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col4", "0.95*hm");
    model.study("std2").feature("uq").setEntry("uboundselection", "col4", "1.05*hm");
    model.study("std2").feature("uq").setEntry("distributionselection", "col5", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col5", "wp");
    model.study("std2").feature("uq").setEntry("s2selection", "col5", "0.01*wp");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col5", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col5", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col5", "0.95*wp");
    model.study("std2").feature("uq").setEntry("uboundselection", "col5", "1.05*wp");
    model.study("std2").feature("uq").setEntry("distributionselection", "col6", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col6", "wf");
    model.study("std2").feature("uq").setEntry("s2selection", "col6", "0.01*wf");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col6", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col6", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col6", "0.95*wf");
    model.study("std2").feature("uq").setEntry("uboundselection", "col6", "1.05*wf");
    model.study("std2").feature("uq").setEntry("distributionselection", "col7", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col7", "r1");
    model.study("std2").feature("uq").setEntry("s2selection", "col7", "0.01*r1");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col7", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col7", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col7", "0.95*r1");
    model.study("std2").feature("uq").setEntry("uboundselection", "col7", "1.05*r1");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so2").set("psol", "sol3");
    model.batch("uq1").run("compute");

    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").feature().copy("uq", "std2/uq", "");
    model.study("std3").feature().copy("ref", "std2/ref", "");
    model.study("std3").feature("uq").set("uqtype", "screening");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "inverseuq");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std3").feature("uq").set("computeaction", "recompute");
    model.study("std3").feature("uq").set("designtable", "new");
    model.study("std3").feature("uq").set("verifyaction", "recompute");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std3").feature("uq").set("tablegraphgrp", "new");
    model.study("std3").label("\u7814\u7a76 3\uff0c\u7075\u654f\u5ea6");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std3");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pd2").feature("so2").set("psol", "sol6");
    model.batch("uq2").run("compute");

    model.result("pg3").run();

    model.study().create("std4");
    model.study("std4").feature().copy("uq", "std3/uq", "");
    model.study("std4").feature().copy("ref", "std3/ref", "");
    model.study("std4").feature("uq").set("uqtype", "screening");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "inverseuq");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "uncertaintypropagation");

    model.func().create("gpm1", "GaussianProcess");
    model.func("gpm1").active(false);

    model.study("std4").feature("uq").set("globalgpfunction", "gpm1");

    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8")
         .addRows(new double[][]{{0.008057758385630113, 0.1912869248802796, 0.348154493342134, 0.10009075938042335, 0.10732779476574882, 0.06356507232668775, 0.024605647249292827, 0.1094966679150851}, {0.008055227728615463, 0.19358543113902704, 0.349470570737835, 0.0997227599066344, 0.10661698815117943, 0.06274247270472594, 0.02531998648802061, 0.11304611966010747}, {0.007948483585129226, 0.1903473575102362, 0.34653499713154506, 0.09986511988924665, 0.10921687716261962, 0.063605591905191, 0.024964229022730156, 0.10810385982760017}, {0.008027466137241741, 0.19410448516778614, 0.34542701700229045, 0.09899186273507125, 0.10844655966688937, 0.06238663175458058, 0.025151793705362496, 0.10476540470327705}, {0.007965064529670605, 0.18830232104867045, 0.3472890238148765, 0.10142792644399991, 0.1057313207247792, 0.062448899378539105, 0.024714184017927025, 0.11191479655868845}, {0.008041907735309033, 0.18620065541760936, 0.34647067760696526, 0.10028416858962866, 0.10738677098606071, 0.062481064557387785, 0.024794808251009322, 0.10803668763198765}, {0.007971970546891199, 0.1921073138534479, 0.35205403413761716, 0.09877441232447297, 0.10831305359334524, 0.06236285541519107, 0.0252989259993414, 0.11360970186940308}, {0.007983045373840796, 0.1890406744811108, 0.34349316253077666, 0.10080970713784299, 0.10736585207976697, 0.061063834223757274, 0.025056257819064072, 0.10289444088988203}, {0.008002886915256585, 0.19236985595698136, 0.34976880357942153, 0.10198780140666185, 0.10874319917230488, 0.061797040731963286, 0.024719019700275232, 0.10354606517249265}, {0.007962901221598909, 0.1919643169902998, 0.3512937123998122, 0.10002443975255551, 0.10712173872761778, 0.06315747415909592, 0.0253509521736375, 0.11711641250893332}, {0.008038348546083113, 0.19041155972557472, 0.34889753061642825, 0.10092984320033288, 0.10692053644032624, 0.061726086366053715, 0.02553677459069443, 0.11078889660931056}, {0.00795119328994162, 0.1881523511557774, 0.35261297376036266, 0.09938908319685938, 0.10695931549474169, 0.06276513347628639, 0.025340500225491912, 0.11983209393510715}, {0.007927394480990174, 0.1888492116155888, 0.35144447050870803, 0.10131039154685076, 0.10774469769713464, 0.061765288512957685, 0.024805856232348527, 0.11059879361221629}, {0.007977304417169555, 0.1897310157448796, 0.34424667700914136, 0.09994170128735647, 0.10673457588500113, 0.06245732849539796, 0.025577579310122705, 0.11111953223444539}, {0.007956261173355458, 0.19027796124264826, 0.35239911312271305, 0.09952447325817243, 0.10779589704562408, 0.06295774542348208, 0.024419453495586232, 0.11289521189858372}, {0.008018909187875406, 0.19150853691707992, 0.35030570866375915, 0.09907538874771481, 0.10856294582834244, 0.06331272306169762, 0.024909731007241923, 0.11099420917916969}, {0.00803468406697995, 0.18919955420550116, 0.35250213924891177, 0.100713418015559, 0.108233243785556, 0.06302195016731826, 0.02448049039772942, 0.11035636372787483}, {0.008005198243253837, 0.1899936743553822, 0.3509722305180608, 0.10116536954744823, 0.10621614654228761, 0.0631871346835653, 0.024530485052246944, 0.11404952977307901}, {0.007967723192488315, 0.1872474254363902, 0.34322784723892896, 0.09959741620404561, 0.10805771958745433, 0.06259569167452354, 0.02503357593548871, 0.10651853092335517}, {0.008031383970553982, 0.190248466258498, 0.35076842705515515, 0.0992511496730492, 0.10636450874576127, 0.06250041490436282, 0.02469865637667993, 0.11345332825983893}, {0.0080487661787513, 0.18913455067174903, 0.3568068125264852, 0.09980613282659362, 0.10885292029590825, 0.06230731932399489, 0.02495346132170071, 0.11381333903556691}, {0.00800822870640686, 0.18968625025387903, 0.347855692643417, 0.09885129996290698, 0.10812766299661432, 0.06288851779276806, 0.025401107279148745, 0.11190180212237236}, {0.008051781707702503, 0.18825655205573613, 0.3527910426037784, 0.0988722205897387, 0.10609889719873539, 0.06257158833535169, 0.025125517673062817, 0.11885352640834759}, {0.007979799874281655, 0.19110165526149478, 0.3470561585682645, 0.1004251076011874, 0.1075568418984578, 0.06254325360691154, 0.02476880937711323, 0.1076455115599218}, {0.007975346404767089, 0.19313024222853384, 0.3540638299102881, 0.1011252825528512, 0.10748405648472491, 0.061881151559859515, 0.025024177068677454, 0.11265391849388166}, {0.008015569785723828, 0.19228695454554595, 0.3590803602288574, 0.0990009463611985, 0.10763009753989498, 0.06239511000582577, 0.024889728231194504, 0.11883695955979685}, {0.007987940636130482, 0.19101313665730388, 0.3533564545267864, 0.10025267536508708, 0.10991999566809241, 0.0634185044841242, 0.025205006083446797, 0.11226983035232102}, {0.00799715047207258, 0.19140404279339898, 0.35299933480371803, 0.09830771489767637, 0.106565552867268, 0.06378262524659192, 0.02497431659997021, 0.12074509039807428}, {0.008071640232791357, 0.19004853306165537, 0.35381010851068684, 0.09977520817004487, 0.10675612915650057, 0.06135971040305497, 0.025048923460417077, 0.11300579247151017}, {0.008000947183109767, 0.19189172621174347, 0.349546360165748, 0.10039780397372074, 0.10512435296354476, 0.062068429151341725, 0.02499427012623179, 0.114715340497679}, {0.008007475586109999, 0.18931781691484523, 0.3552643408502836, 0.10055400899158995, 0.10648583856402093, 0.06285952150466333, 0.02508975208291103, 0.12001040095802341}, {0.008068602108587989, 0.18842712849027957, 0.3522093853111305, 0.10121658837483713, 0.10816599963409297, 0.06277821644445776, 0.025167668838313048, 0.11181469014732709}, {0.007958501802906158, 0.18862828185682173, 0.35059411980584493, 0.0996937303936217, 0.10552475170949309, 0.06339882604750102, 0.024871878525395817, 0.12010726200875431}, {0.00792459290153524, 0.18937996550506972, 0.35003234189867277, 0.1008615479213469, 0.10808593405708343, 0.06281490424115035, 0.02537177203626402, 0.11394698753857761}, {0.008012288207872241, 0.19282510376783354, 0.35355493877778954, 0.1006210557116932, 0.1075826921891733, 0.06322879187447109, 0.024887442013123057, 0.11431047198560328}, {0.008044255461468997, 0.18958530498320403, 0.35074242231840097, 0.10172954372188285, 0.10710485061396897, 0.062212067945872605, 0.024907140547584666, 0.11002318413447391}, {0.008017529724700045, 0.1901653590897548, 0.355931524002291, 0.10036137523828441, 0.10783192684130623, 0.06218572394530429, 0.025460997550326544, 0.117209525088789}, {0.008009979479594002, 0.18807966464967094, 0.35444182093292387, 0.0999943025502937, 0.10705197503777779, 0.06186509656395104, 0.02464024129606586, 0.11403663561492738}, {0.007930247051252351, 0.19091785661051647, 0.35195998935818706, 0.10015931500274493, 0.10949779796201219, 0.06228770595964538, 0.024962695929425564, 0.1092591682004152}, {0.007945954889696634, 0.19120386778977883, 0.3510624174748961, 0.0995018442591571, 0.1073042043492096, 0.06145602346415242, 0.024755556363558716, 0.1105296687259309}, {0.007921611499226655, 0.1906412249285031, 0.3478803904559325, 0.10242340940140947, 0.10666144151394072, 0.06255940672479922, 0.025076892671137125, 0.11155343742619676}, {0.007935821365844132, 0.19171825321042316, 0.35322107353866655, 0.10030479635848714, 0.10599172507400596, 0.06265894316206218, 0.024825366443330915, 0.11831721364497766}, {0.007952777843710093, 0.1889274954917342, 0.3483809702099503, 0.09929468147236897, 0.10839724853869527, 0.061231338899198594, 0.02517935814687333, 0.10779016171705368}, {0.007973140860251632, 0.1906005712095359, 0.3549164700221876, 0.0991406199911761, 0.10588236435027418, 0.06205758524108397, 0.02506544312057749, 0.12038852774710655}, {0.007982035671871267, 0.18784458509805171, 0.34933591775841205, 0.09964498052933825, 0.10615872020885117, 0.06216036520755555, 0.024988301894542223, 0.1146799284218808}, {0.007999349358406571, 0.19007065393958722, 0.34759464448548877, 0.10105371744238724, 0.10718407753651409, 0.06401251628883624, 0.025092818673289507, 0.11345025093303504}, {0.007934353268216423, 0.1876128026848902, 0.34854658568491975, 0.10095528314660034, 0.10795048065223262, 0.06304003688608859, 0.024851853938727037, 0.11133972805352982}, {0.007969093496122322, 0.18752288265865444, 0.3517284900169989, 0.09932381632461405, 0.10960833855145798, 0.06272595830637012, 0.025139859843611577, 0.11177241513795043}, {0.008075188734134523, 0.18991280128843033, 0.3487240222273809, 0.09721715130016394, 0.10765996393473233, 0.06223243571193038, 0.025017735954989483, 0.11026271947330404}, {0.00805252387083776, 0.19075357861496542, 0.345723381614199, 0.10018070447263101, 0.1068778307802921, 0.06155715960815297, 0.024670655378196734, 0.10398409640166834}, {0.008033713535049301, 0.1886880833294838, 0.3449489620365131, 0.10075810845492182, 0.10862705524985032, 0.06282971404007226, 0.02526800356929776, 0.10582853111787358}, {0.008077618067688747, 0.19092041719848446, 0.354759961164058, 0.09944986246660995, 0.10786459060582636, 0.06291927032751998, 0.025192114110960007, 0.11575759595333733}, {0.007992067912085203, 0.18857810793697943, 0.35043425838726083, 0.10165449304457258, 0.10915716459317093, 0.062128222547747546, 0.02511687768937073, 0.10750110651376739}, {0.008046865043483129, 0.1926844684171067, 0.3501663150296649, 0.10066541787805734, 0.10825473969222373, 0.06241216127442627, 0.02511006851926226, 0.108087793541513}, {0.007989377504089146, 0.18794951035931193, 0.3499263256008552, 0.09991747594400623, 0.10896678165178583, 0.062324078089755236, 0.024589198969241433, 0.10709924519111816}, {0.007941896933643361, 0.189646015856157, 0.351195765308047, 0.10050273168481592, 0.10701987206458358, 0.061651212752937674, 0.025253993201453037, 0.11389117655957594}, {0.007944193781245276, 0.18883737968420014, 0.35660465376017436, 0.09869313055410421, 0.1085080925475601, 0.06210862330976536, 0.02483149273998015, 0.11644191329730627}, {0.00799430087257185, 0.1916294534367385, 0.34605008474798904, 0.09956274026179775, 0.10534297665536005, 0.06309495091540994, 0.02493780402744054, 0.11400655996628062}, {0.008025352878029832, 0.1943860639871498, 0.34864590740128154, 0.09845181802707632, 0.10684533706889683, 0.06161539502942881, 0.024935260839950325, 0.10862393659871088}, {0.00806467656885985, 0.18691761244658045, 0.34754910780254944, 0.1000551767344342, 0.10645574825249549, 0.06308050685705253, 0.025158722666621346, 0.11361469356866676}, {0.007961027511888666, 0.19248176042971496, 0.3470238776317501, 0.1004688604161907, 0.10799420554348803, 0.06224926380410656, 0.02522549641994129, 0.10785912661923995}, {0.008065313530622285, 0.19054259943129928, 0.3403358102404257, 0.1001186283542532, 0.10725520763572345, 0.06261328377714251, 0.025043337395067172, 0.10232022847428626}, {0.008020570758226101, 0.18928652694458672, 0.34591758470325984, 0.09783829819162908, 0.10752848690957414, 0.06327114052844338, 0.024845146155512768, 0.11048959840701644}, {0.008039858637800032, 0.18718624576777712, 0.3491519416088916, 0.09983650316709523, 0.107924080738036, 0.06202069829051019, 0.025239453711438525, 0.11013758051482841}, {0.008028143829466493, 0.18500925190130804, 0.3516921491321431, 0.09917982426659772, 0.10771477672556772, 0.06298405861781939, 0.02486847040414267, 0.11521812648063912}, {0.007984872574963475, 0.18646048052714567, 0.3490485501792754, 0.10149554765149249, 0.10632070443669836, 0.06263686630818448, 0.025219423184691506, 0.11518486180684268}, {0.007938588850966368, 0.19081876808264822, 0.3482143643283889, 0.09858621513203823, 0.10744682094042624, 0.06268788502522799, 0.025005960230443266, 0.11277815625165258}, {0.007993325843677682, 0.18949529137787824, 0.3445614956054449, 0.09821557351222651, 0.1072179643323368, 0.061945584228001165, 0.024779253815487397, 0.10664351543707942}, {0.008021855069327948, 0.19146837610258227, 0.3496308298639609, 0.09939607420129622, 0.10866671076198223, 0.061992898754198895, 0.024735779872029795, 0.10595971171846151}, {0.008062121734515955, 0.18983002627443624, 0.34671830298274764, 0.10058326305735303, 0.10906181285165198, 0.0619405376686681, 0.02491943526345481, 0.10219608623771621}});

    model.func("gpm1").set("source", "resultTable");
    model.func("gpm1").set("resultTable", "tbl8");
    model.func("gpm1").set("ignorenaninf", true);

    model.study("std4").feature("uq").set("designtable", "tbl8");

    model.func("gpm1").set("covfunction", "matern32");
    model.func("gpm1").set("meanfunction", "const");
    model.func("gpm1").set("improvementfunction", "entropy");
    model.func("gpm1").set("lastinternalseed", 1014);
    model.func("gpm1").set("gpadpoptmethod", "direct");
    model.func("gpm1").set("maxgpevals", 10000);
    model.func("gpm1").set("maxgpiters", 500);
    model.func("gpm1").set("adpevals", 10000);
    model.func("gpm1").set("setupfromstudy", "on");
    model.func("gpm1").setEntry("distributionselection", "col1", "normal");
    model.func("gpm1").setEntry("s1selection", "col1", "0.008");
    model.func("gpm1").setEntry("s2selection", "col1", "8.0E-5");
    model.func("gpm1").setEntry("lboundselection", "col1", "0.00792");
    model.func("gpm1").setEntry("uboundselection", "col1", "0.00808");
    model.func("gpm1").setEntry("lcdfselection", "col1", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col1", "manual");
    model.func("gpm1").setEntry("distributionselection", "col2", "normal");
    model.func("gpm1").setEntry("s1selection", "col2", "0.19");
    model.func("gpm1").setEntry("s2selection", "col2", "0.0019");
    model.func("gpm1").setEntry("lboundselection", "col2", "0.1805");
    model.func("gpm1").setEntry("uboundselection", "col2", "0.1995");
    model.func("gpm1").setEntry("lcdfselection", "col2", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col2", "manual");
    model.func("gpm1").setEntry("distributionselection", "col3", "normal");
    model.func("gpm1").setEntry("s1selection", "col3", "0.35");
    model.func("gpm1").setEntry("s2selection", "col3", "0.0034999999999999996");
    model.func("gpm1").setEntry("lboundselection", "col3", "0.33249999999999996");
    model.func("gpm1").setEntry("uboundselection", "col3", "0.3675");
    model.func("gpm1").setEntry("lcdfselection", "col3", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col3", "manual");
    model.func("gpm1").setEntry("distributionselection", "col4", "normal");
    model.func("gpm1").setEntry("s1selection", "col4", "0.1");
    model.func("gpm1").setEntry("s2selection", "col4", "0.001");
    model.func("gpm1").setEntry("lboundselection", "col4", "0.095");
    model.func("gpm1").setEntry("uboundselection", "col4", "0.10500000000000001");
    model.func("gpm1").setEntry("lcdfselection", "col4", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col4", "manual");
    model.func("gpm1").setEntry("distributionselection", "col5", "normal");
    model.func("gpm1").setEntry("s1selection", "col5", "0.1075");
    model.func("gpm1").setEntry("s2selection", "col5", "0.001075");
    model.func("gpm1").setEntry("lboundselection", "col5", "0.102125");
    model.func("gpm1").setEntry("uboundselection", "col5", "0.112875");
    model.func("gpm1").setEntry("lcdfselection", "col5", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col5", "manual");
    model.func("gpm1").setEntry("distributionselection", "col6", "normal");
    model.func("gpm1").setEntry("s1selection", "col6", "0.0625");
    model.func("gpm1").setEntry("s2selection", "col6", "6.25E-4");
    model.func("gpm1").setEntry("lboundselection", "col6", "0.059375");
    model.func("gpm1").setEntry("uboundselection", "col6", "0.065625");
    model.func("gpm1").setEntry("lcdfselection", "col6", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col6", "manual");
    model.func("gpm1").setEntry("distributionselection", "col7", "normal");
    model.func("gpm1").setEntry("s1selection", "col7", "0.025");
    model.func("gpm1").setEntry("s2selection", "col7", "2.5E-4");
    model.func("gpm1").setEntry("lboundselection", "col7", "0.02375");
    model.func("gpm1").setEntry("uboundselection", "col7", "0.026250000000000002");
    model.func("gpm1").setEntry("lcdfselection", "col7", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col7", "manual");
    model.func("gpm1").setEntry("args", "col1", "ts");
    model.func("gpm1").setEntry("unit", "col1", "m");
    model.func("gpm1").setEntry("columnType", "col2", "arg");
    model.func("gpm1").setEntry("args", "col2", "lp");
    model.func("gpm1").setEntry("unit", "col2", "m");
    model.func("gpm1").setEntry("columnType", "col3", "arg");
    model.func("gpm1").setEntry("args", "col3", "ls");
    model.func("gpm1").setEntry("unit", "col3", "m");
    model.func("gpm1").setEntry("columnType", "col4", "arg");
    model.func("gpm1").setEntry("args", "col4", "hm");
    model.func("gpm1").setEntry("unit", "col4", "m");
    model.func("gpm1").setEntry("columnType", "col5", "arg");
    model.func("gpm1").setEntry("args", "col5", "wp");
    model.func("gpm1").setEntry("unit", "col5", "m");
    model.func("gpm1").setEntry("columnType", "col6", "arg");
    model.func("gpm1").setEntry("args", "col6", "wf");
    model.func("gpm1").setEntry("unit", "col6", "m");
    model.func("gpm1").setEntry("columnType", "col7", "arg");
    model.func("gpm1").setEntry("args", "col7", "r1");
    model.func("gpm1").setEntry("unit", "col7", "m");
    model.func("gpm1").setEntry("funcs", "col8", "gpm1_1");

    model.study("std4").feature("uq").set("computeaction", "append");
    model.study("std4").feature("uq").set("tablegraphgrp", "new");
    model.study("std4").label("\u7814\u7a76 4\uff0c\u4f20\u64ad");
    model.study("std4").feature("uq").set("selected", new String[]{"0", "1", "3", "5", "6"});
    model.study("std4").feature("uq").set("distributionselection", new String[]{"col1", "normal", "col2", "normal"});
    model.study("std4").feature("uq").set("s1selection", new String[]{"col1", "ls", "col2", "wp"});
    model.study("std4").feature("uq").set("s2selection", new String[]{"col1", "0.01*ls", "col2", "0.01*wp"});
    model.study("std4").feature("uq").set("lcdfselection", new String[]{"col1", "manual", "col2", "manual"});
    model.study("std4").feature("uq").set("lboundselection", new String[]{"col1", "0.95*ls", "col2", "0.95*wp"});
    model.study("std4").feature("uq").set("ucdfselection", new String[]{"col1", "manual", "col2", "manual"});
    model.study("std4").feature("uq").set("uboundselection", new String[]{"col1", "1.05*ls", "col2", "1.05*wp"});
    model.study("std4").feature("uq").set("punitselection", new String[]{"col1", "m", "col2", "m"});
    model.study("std4").feature("uq").set("inputdatasource", new String[]{"col1", "specified", "col2", "specified"});
    model.study("std4").feature("uq").set("tablecolumnselection", new String[]{"col1", "", "col2", ""});
    model.study("std4").feature("uq").set("plistarrselection", new String[]{"col1", "", "col2", ""});
    model.study("std4").feature("uq").set("plistarrsizeselection", new String[]{"col1", "", "col2", ""});
    model.study("std4").feature("uq").remove("sourceType", new int[]{0, 1, 3, 5, 6});
    model.study("std4").feature("uq").remove("paramDescription", new int[]{0, 1, 3, 5, 6});
    model.study("std4").feature("uq").remove("pname", new int[]{0, 1, 3, 5, 6});
    model.study("std4").feature("uq").set("computeaction", "recompute");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std4");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("pd3").feature("so2").set("psol", "sol9");
    model.batch("uq3").run("compute");

    model.result("pg4").run();
    model.result("pg4").run();

    model.study().create("std5");
    model.study("std5").feature().copy("uq", "std4/uq", "");
    model.study("std5").feature().copy("ref", "std4/ref", "");
    model.study("std5").feature("uq").set("uqtype", "screening");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "inverseuq");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "reliabilityanalysis");

    model.func().duplicate("gpm2", "gpm1");

    model.study("std5").feature("uq").set("globalgpfunction", "gpm2");

    model.result().table().create("tbl17", "Table");
    model.result().table("tbl17")
         .addRows(new double[][]{{0.3517193049276792, 0.10686206001429008, 0.11469287287721869}, {0.3484473017073407, 0.10855925422332528, 0.10793549358103764}, {0.35617703042933946, 0.10707186384769125, 0.11854871540242247}, {0.35544603496383675, 0.10817836195086715, 0.11531890561656977}, {0.35089346728972526, 0.10767002976712042, 0.11208549203333615}, {0.35030511155306193, 0.10883474239930621, 0.10900979964947466}, {0.34875753745010407, 0.10779691778289444, 0.10991713232989028}, {0.34753767325408286, 0.10719267935489925, 0.11002750265211697}, {0.3538907400693961, 0.10620641148263481, 0.11838193190108354}, {0.35067805437411276, 0.1051950798583593, 0.11773614712654243}, {0.34654736512378714, 0.10801088465332824, 0.10742331300114502}, {0.3529414819735719, 0.11021853176295356, 0.1083618364358182}, {0.34967552758596115, 0.10655655060248807, 0.11350243960436054}, {0.34555718835459504, 0.1089342055225433, 0.10457885669139315}, {0.35330295325574484, 0.10761804865222381, 0.11445534506670287}, {0.3518371526225223, 0.10822761227437562, 0.11185598918562017}, {0.3423683373915222, 0.10746315162584537, 0.10478391066281502}, {0.3495366623607657, 0.10726979323381244, 0.11169259180547232}, {0.3443383471137813, 0.10663485699740795, 0.10839860425204886}, {0.3479123244250517, 0.10576477605061921, 0.11363818684293829}, {0.36649141747750646, 0.11256522108237699, 0.11558150867379885}, {0.36649141747750646, 0.10963879191258379, 0.12242760056730013}, {0.36649141747750646, 0.1024347789175787, 0.14162218394251636}, {0.3335085825223492, 0.1024347789175787, 0.10791950017884276}, {0.3335085825223492, 0.11256522108237699, 0.08747192519659779}, {0.36160597048284004, 0.1024347789175787, 0.13628529434012923}, {0.36375200486212406, 0.1043760148395048, 0.13313038053804382}, {0.36050706399353866, 0.11158359283252871, 0.11225160432773983}, {0.33951661503656994, 0.11256522108237699, 0.09228438111445318}, {0.3364168076231177, 0.11071989681019632, 0.09309077930059646}, {0.33951661503656994, 0.1024347789175787, 0.11363576187322236}, {0.33588558333622925, 0.10402277594309785, 0.10647381922108343}, {0.35786196942809534, 0.10337167352849787, 0.12973908044882543}, {0.3376385179142119, 0.1096126802723156, 0.09636069352330044}, {0.3598827702902091, 0.10546762800309203, 0.12617298131569127}, {0.3335085825223492, 0.10538728475300915, 0.10129057698490382}, {0.36331814314722116, 0.11034054347862703, 0.11770303428070868}, {0.34137385214511057, 0.11163652746286952, 0.09545544717716332}, {0.3421382363252566, 0.10314703912989658, 0.11447991177601917}, {0.3404461845413536, 0.1045700073600618, 0.10945978235004346}});

    model.func("gpm2").set("source", "resultTable");
    model.func("gpm2").set("resultTable", "tbl17");
    model.func("gpm2").set("ignorenaninf", true);

    model.study("std5").feature("uq").set("designtable", "tbl17");

    model.func("gpm2").setEntry("funcs", "col3", "gpm2_1");

    model.study("std5").feature("uq").set("computeaction", "append");
    model.study("std5").feature("uq").set("tablegraphgrp", "new");
    model.study("std5").label("\u7814\u7a76 5\uff0c\u53ef\u9760\u6027");
    model.study("std5").feature("uq").setIndex("threshold", 0.12, 0);
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol12");
    model.sol("sol12").study("std5");
    model.sol("sol12").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("pd4").feature("so2").set("psol", "sol12");
    model.batch("uq4").run("compute");

    model.result("pg3").run();

    model.study("std5").createAutoSequences("all");

    model.batch("uq4").run("postprocess");

    model.result("pg5").run();

    model.title("\u652f\u67b6\u7684\u4e0d\u786e\u5b9a\u6027\u91cf\u5316");

    model
         .description("\u672c\u6a21\u578b\u662f\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u652f\u67b6\u6559\u5b66\u6a21\u578b\u7684\u4e00\u4e2a\u7248\u672c\uff0c\u91c7\u7528\u5b8c\u5168\u53c2\u6570\u5316\u7684\u51e0\u4f55\u7ed3\u6784\uff0c\u901a\u8fc7\u8fd0\u884c\u4e00\u7cfb\u5217\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u7814\u7a76\uff0c\u8bc4\u4f30\u54ea\u4e9b\u51e0\u4f55\u53c2\u6570\u5bf9\u9500\u5b54\u89d2\u5ea6\u7684\u5f71\u54cd\u6700\u5927\uff0c\u5e76\u8ba1\u7b97\u9500\u5b54\u89d2\u5ea6\u8d85\u8fc7 0.1\u00a0\u5ea6\u7684\u6982\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("bracket_uncertainty_quantification.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
