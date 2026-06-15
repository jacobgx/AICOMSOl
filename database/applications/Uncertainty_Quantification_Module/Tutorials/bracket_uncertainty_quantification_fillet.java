/*
 * bracket_uncertainty_quantification_fillet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:33 by COMSOL 6.3.0.290. */
public class bracket_uncertainty_quantification_fillet {

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

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1")
         .insertFile("bracket_uncertainty_quantification_fillet_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige1");

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
    model.component("comp1").selection("sel1").add(20, 21, 22, 23, 24, 25, 26, 27, 37, 38, 39, 40, 41, 42, 43, 44);
    model.component("comp1").selection("sel1").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").add(4, 5, 7, 8);
    model.component("comp1").selection("sel2").label("\u5de6\u9500\u5b54");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").add(52, 53, 54, 55);
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
    model.study("std2").feature("uq").setIndex("pname", "fr1", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 4);
    model.study("std2").feature("uq").setIndex("pname", "fr1", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 4);
    model.study("std2").feature("uq").setIndex("pname", "hf", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 5);
    model.study("std2").feature("uq").setIndex("pname", "hf", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 5);
    model.study("std2").feature("uq").setIndex("pname", "hm", 6);
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "hm", 6);
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "hmax", 7);
    model.study("std2").feature("uq").setEntry("sourceType", "col8", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 7);
    model.study("std2").feature("uq").setIndex("pname", "hmax", 7);
    model.study("std2").feature("uq").setEntry("sourceType", "col8", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 7);
    model.study("std2").feature("uq").setIndex("pname", "ts", 0);
    model.study("std2").feature("uq").setIndex("pname", "lp", 1);
    model.study("std2").feature("uq").setIndex("pname", "ls", 2);
    model.study("std2").feature("uq").setIndex("pname", "hm", 3);
    model.study("std2").feature("uq").setIndex("pname", "wp", 4);
    model.study("std2").feature("uq").setIndex("pname", "wf", 5);
    model.study("std2").feature("uq").setIndex("pname", "fr1", 6);
    model.study("std2").feature("uq").setIndex("pname", "r1", 7);
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
    model.study("std2").feature("uq").setEntry("s1selection", "col7", "fr1");
    model.study("std2").feature("uq").setEntry("s2selection", "col7", "0.01*fr1");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col7", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col7", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col7", "0.95*fr1");
    model.study("std2").feature("uq").setEntry("uboundselection", "col7", "1.05*fr1");
    model.study("std2").feature("uq").setEntry("distributionselection", "col8", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col8", "r1");
    model.study("std2").feature("uq").setEntry("s2selection", "col8", "0.01*r1");
    model.study("std2").feature("uq").setEntry("lcdfselection", "col8", "manual");
    model.study("std2").feature("uq").setEntry("ucdfselection", "col8", "manual");
    model.study("std2").feature("uq").setEntry("lboundselection", "col8", "0.95*r1");
    model.study("std2").feature("uq").setEntry("uboundselection", "col8", "1.05*r1");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so2").set("psol", "sol3");
    model.batch("uq1").run("compute");

    return model;
  }

  public static Model run2(Model model) {

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
         .addRows(new double[][]{{0.007958597674486523, 0.18895743469384718, 0.35307575896668186, 0.1002809914335596, 0.10976518529066942, 0.062248891639983116, 0.010174681480117357, 0.02495324022306348, 0.088573245306332}, {0.007992586638774148, 0.18886333287107623, 0.3486056449965063, 0.09807598159378125, 0.10930969104242845, 0.0626657041425629, 0.009941798169364465, 0.02465684789990323, 0.08722709027806139}, {0.007956711410372285, 0.1916394617079586, 0.3497410756205456, 0.10196928793941921, 0.10720783893148912, 0.0620933050768951, 0.010248726979026769, 0.024728134512283752, 0.08751820215063646}, {0.008056485351669623, 0.1906507122801963, 0.3534109789356247, 0.09883215687734383, 0.10658912646342307, 0.06211575721272279, 0.010086043705123452, 0.024991286438270713, 0.09351871567100868}, {0.007948398174250594, 0.19212267379212897, 0.34836881149346666, 0.0996667551070579, 0.10573688166253158, 0.062293249233792244, 0.010059895987549968, 0.024905152337640072, 0.09191410410743907}, {0.008068376889357446, 0.18981033790730287, 0.34682139487268127, 0.09854824046766004, 0.1072438740211783, 0.06258020956817717, 0.010093777067727844, 0.02485933908983737, 0.08799198627179138}, {0.007988067051296462, 0.19015684866312488, 0.3561340996954338, 0.10031049956822143, 0.1087705911292454, 0.06160662469671867, 0.009952635894046942, 0.024890704466526154, 0.09087669865055686}, {0.008050532952463286, 0.18943149947004936, 0.35187185863461634, 0.10126585007077225, 0.10443648200758626, 0.062480058820298834, 0.009975405588196016, 0.024700093601375008, 0.0951290281571207}, {0.008051797329758685, 0.19267454536938508, 0.3523085529056848, 0.09994593987210519, 0.10651480176652767, 0.06297790405632374, 0.009916109632806203, 0.02520203747553512, 0.09457071463604527}, {0.007973098331600965, 0.19152331209713874, 0.35073014744105596, 0.09929079112594116, 0.10694915812865437, 0.06157885751993172, 0.010070810709537407, 0.025445750833666538, 0.09213996768578717}, {0.008027718408244589, 0.19172249518039935, 0.34903236553878636, 0.10117015363708601, 0.10617776283797589, 0.06175898582558079, 0.009972036800578279, 0.025223212459284033, 0.09001894792972855}, {0.008023378698560815, 0.19025532061205463, 0.3537598261108304, 0.09971702658679188, 0.10865293850626201, 0.06305450453329824, 0.009778285706013176, 0.025086781552309274, 0.09315740729133552}, {0.007963095672272952, 0.18910391341054192, 0.3492240871801174, 0.09979287803348424, 0.10901271919708756, 0.06363430521097745, 0.010082645742183562, 0.024886252794875448, 0.08978703936914756}, {0.007929750584933882, 0.18508966154739778, 0.3503188307634585, 0.1004123747568286, 0.10687992909425446, 0.06287651362231617, 0.009930149018552296, 0.024937631402786164, 0.0948039641626899}, {0.007933707651286797, 0.18995392425938576, 0.35268707263943794, 0.10036214524454025, 0.10691844052240188, 0.06315413822841943, 0.010100517376030464, 0.024866558079750398, 0.0950174251440382}, {0.008000640457370034, 0.18847951058779452, 0.3563156472155286, 0.09941292093140021, 0.10682696600920731, 0.062488382126835135, 0.009883859368736293, 0.02487375483406289, 0.09732634047595295}, {0.00797678267341308, 0.18920827664159878, 0.34712241018898654, 0.1016430422767819, 0.10738554445798859, 0.061918718391390626, 0.009825312010214188, 0.024902745938114937, 0.08712298252344527}, {0.008035528185586473, 0.19243487301476336, 0.34368461827338137, 0.10045380008858187, 0.10732410643430285, 0.06235482605823851, 0.010075986577427304, 0.024975127054956303, 0.08422194980686513}, {0.008011745552509874, 0.18990493741342365, 0.3514683242839105, 0.10146691707305351, 0.10702340037530353, 0.06346246343691103, 0.009863555800876842, 0.02500606271088005, 0.09380482429236102}, {0.007941575429948325, 0.185994414339231, 0.34626751834322256, 0.10001944198132116, 0.10787931850722918, 0.06205510721373683, 0.010051268096556999, 0.02492423600784745, 0.0877631777693143}, {0.007995601500922801, 0.1864810429960393, 0.3516585388389846, 0.1009115561293114, 0.10766048684361675, 0.062369332659540866, 0.010038011110812806, 0.02453340097095241, 0.08992999565838261}, {0.007952684394163897, 0.1892780105030637, 0.3442830783780561, 0.09989816504812414, 0.10630292769628687, 0.06331849078071818, 0.01004873130188056, 0.024833711308554048, 0.09014766073468973}, {0.007954028217234281, 0.18986514421224857, 0.35006323478332174, 0.09960829781807963, 0.1063994354028399, 0.061409687005873265, 0.009908125772401645, 0.02503767900104029, 0.09169819321168877}, {0.008004148582422476, 0.1868782623990714, 0.34383368315595436, 0.10042951658637024, 0.10845504772114395, 0.06274781637254896, 0.009932895171589825, 0.02481007170611509, 0.08483076054432198}, {0.007960618109291847, 0.18808851956847822, 0.35134643941585636, 0.09779643435008085, 0.10601114887565485, 0.06380179812365959, 0.009984543486230802, 0.024676625519647485, 0.09837154440263053}, {0.007986387966999334, 0.19125056469326723, 0.3525178934026168, 0.1008246921586828, 0.1088503732372001, 0.0630241777811262, 0.010014767840949864, 0.024620360752050072, 0.08903977948905668}, {0.007968589529572813, 0.1884947970084675, 0.3496613644659456, 0.09839450123723377, 0.10708274914031671, 0.06268350089105323, 0.010206943579852364, 0.02496320465693271, 0.09266740151091173}, {0.007983931961029487, 0.18937960106195742, 0.3520519607098457, 0.09916528177247537, 0.10698471100179247, 0.06279788199115613, 0.009993115820725386, 0.025631079437233147, 0.09707875833038877}, {0.008043001964160197, 0.18904454800553122, 0.34842248303993717, 0.09970715718577923, 0.1067026548337233, 0.06329333152973095, 0.009937148253871708, 0.024725452501475797, 0.09125895543923356}, {0.008005771490193157, 0.18753745154873241, 0.3509416537391102, 0.09936342018089543, 0.107481500223417, 0.06091283268586063, 0.010092081132496126, 0.025133207016540156, 0.08947228129991876}, {0.008053628617947674, 0.19035181647935268, 0.35049361568794674, 0.09963467125345608, 0.10619671793324693, 0.06338349005836329, 0.01013984832884747, 0.025169062695032017, 0.0947072672968988}, {0.007949924496275669, 0.19197649436629263, 0.3521536895282479, 0.09925495170761127, 0.10959048715376722, 0.06263281380818185, 0.010021091245606928, 0.025026489361102695, 0.08952475428796001}, {0.007920242223473848, 0.1895626886855615, 0.34741025840204737, 0.10113497120946384, 0.10780899848807761, 0.06272436783036087, 0.0100029828648791, 0.02440761901365254, 0.086928928098689}, {0.007927528846857997, 0.19022054239297867, 0.34892276547066137, 0.09730196703109463, 0.10727044280801726, 0.062706825474589, 0.009945583317256975, 0.02495264897866678, 0.09324851554082923}, {0.00796113024608003, 0.1903738285963467, 0.35386344483346066, 0.1018686547686896, 0.105469078218685, 0.062223423329575346, 0.009928054285780597, 0.024983780361068304, 0.09617067643617473}, {0.008020182786701795, 0.19097089961555097, 0.35013367238021975, 0.09984546721190214, 0.10858567954617775, 0.06111123578080848, 0.010115366421218937, 0.02482749423952467, 0.08482063225186007}, {0.008040160507451433, 0.19419830650826597, 0.3505583454653667, 0.10051031021064047, 0.10681234389808361, 0.06322583889548873, 0.010027894627998168, 0.024781337462761465, 0.09071009167121602}, {0.008066943059795292, 0.18764138294989513, 0.3481242895696213, 0.09865192754351948, 0.10777771716557993, 0.06257525080409483, 0.009903273384905965, 0.025042274101639322, 0.08972184205743405}, {0.007998110050942791, 0.18960448090828866, 0.3464842990806214, 0.10167540114974799, 0.10913237116499935, 0.062433352205901155, 0.010124017906346153, 0.024932800248984192, 0.0837337992579916}, {0.0080612347230839, 0.19117919194109123, 0.3475164824344167, 0.09952823617386122, 0.10919175840406398, 0.0628346247956421, 0.010063578279821, 0.025343049848503607, 0.0866721959947579}, {0.008078641114313546, 0.19180380396798294, 0.3482377114505133, 0.09912968590968414, 0.10712007817890323, 0.06218655126300845, 0.009983288386993222, 0.025268779082523375, 0.0892348840298474}, {0.00797969821045468, 0.18822999272711935, 0.34882916602970976, 0.1006198375040686, 0.10825476015505646, 0.06292838166795271, 0.00989571821643252, 0.025323268512731152, 0.09102571266010791}, {0.008015890834806019, 0.18802919491200548, 0.3461411416361728, 0.09909111932478162, 0.1057099715257825, 0.06254474873662391, 0.010106646095599473, 0.02542360159642741, 0.09310340709809357}, {0.007942695652424106, 0.19058598862565637, 0.34700766062263044, 0.09859718505877739, 0.108526648046742, 0.061659965056511966, 0.010071638351127477, 0.025017105304110672, 0.08636307515053766}, {0.008037494634779953, 0.1936029489466136, 0.35490068357486476, 0.09977707862426125, 0.1081509127738355, 0.06240308745743443, 0.010023398299649294, 0.02527353231371103, 0.09250726029649693}, {0.008048978890790889, 0.19006093867203408, 0.3541902131119541, 0.09826719107107511, 0.10872811586765375, 0.06253728379011092, 0.01001663715216717, 0.024844588037700562, 0.09141773645112004}, {0.007993917518520445, 0.19459947540114036, 0.3493563600961349, 0.10068436050484851, 0.10837225420844733, 0.062400547298608944, 0.010190255929416202, 0.025409323891946327, 0.08784935123699432}, {0.007940077101798169, 0.18836409501565024, 0.3524440413206317, 0.09905329474942194, 0.10777225714814379, 0.061876290152491124, 0.009995727163946085, 0.024685486592305163, 0.09145492177968616}, {0.00803295027534751, 0.18859639471609688, 0.3513730876659122, 0.10066233855519585, 0.10713996127786891, 0.06213510872153952, 0.009817364176031913, 0.02519659841563564, 0.0922279998194481}, {0.008021534980932289, 0.18739084855674815, 0.3546567204908225, 0.09999936733560223, 0.10758700285093295, 0.06297095694000077, 0.010157056697590528, 0.025051493979405936, 0.09515368024987637}, {0.007944578752558922, 0.18769862278546995, 0.3551322892217796, 0.10053042929549237, 0.10768040466121633, 0.06202625299151911, 0.009968523596859962, 0.02521689811754018, 0.09531210661356015}, {0.008077168067066915, 0.18816229945106736, 0.3582602216935112, 0.09956593469356688, 0.10795797271765302, 0.06227044941936987, 0.009988006205580527, 0.0252394666470494, 0.09630473975785828}, {0.008046782522823377, 0.18720259857863863, 0.3399386636441569, 0.10013836754059784, 0.10638010753847676, 0.06198681962682917, 0.009957229665885324, 0.025153421608402126, 0.0846593578869678}, {0.00801530282520104, 0.1914955703485446, 0.3495529214365107, 0.09877549890558811, 0.10771949292192694, 0.06398922361580654, 0.009998599309352268, 0.024997451866397527, 0.09288724364500192}, {0.0079817694725591, 0.19280168482127555, 0.3528105924127481, 0.09893889259818005, 0.10740361261021622, 0.06281559516134859, 0.010112342840496077, 0.024606422057635247, 0.09189530466112944}, {0.00800761208106039, 0.18876239120755398, 0.34785638041546485, 0.09948303107548005, 0.10527701812110957, 0.061889804844428374, 0.010028946244198358, 0.02475471590442836, 0.0912498241451322}, {0.008062882682914956, 0.1895072526699748, 0.3510703545087886, 0.09944043959587692, 0.1075567781457043, 0.06130247195034605, 0.009947718371306086, 0.024743637125070484, 0.08773666264640327}, {0.00797838157640351, 0.19110549178583566, 0.3479451375283014, 0.10139608820114294, 0.10762699680991512, 0.06354385940402227, 0.010045248102652308, 0.025062181338143356, 0.09013129600659239}, {0.007973991903729823, 0.18889741489617914, 0.34776346108292083, 0.10096561117411042, 0.106065596558798, 0.062307381363189716, 0.010057206069071897, 0.02512469784605062, 0.09143177053369034}, {0.00793089967601868, 0.19084854136561802, 0.34925026805795173, 0.10076570350202288, 0.1079963351192121, 0.061966935799502215, 0.010011342342804005, 0.0250887845047563, 0.08871300909132254}, {0.00807229448575069, 0.1905420115971824, 0.34984838810742497, 0.10237237800385901, 0.10787277032946714, 0.062155804845499596, 0.009980791602190413, 0.024917372860829144, 0.08635053592416644}, {0.007966335592243926, 0.1922376373571468, 0.3532352919252187, 0.10009355328618032, 0.10734610737839127, 0.0626521839654126, 0.009851020932904849, 0.02477815933005695, 0.09297081477934023}, {0.007971490937878058, 0.19292585599565304, 0.34664302295856725, 0.09938054836903562, 0.10805227365825985, 0.06244953502040387, 0.009966271103104246, 0.025364716193948644, 0.08838661690342973}, {0.008024924091581674, 0.19076379465513907, 0.34874394586797824, 0.09992052757567543, 0.10888299155532759, 0.061511020246074175, 0.00988834230223335, 0.02510364218725204, 0.08549424594884768}, {0.007936700908968558, 0.19254071065673573, 0.35113637234264483, 0.10034617427416684, 0.10667053654513192, 0.06286872225020934, 0.009962223116320078, 0.025258127920696274, 0.0948064484979878}, {0.0079995751750089, 0.19047346032835663, 0.34536526666330286, 0.09981585630663266, 0.10646083008589088, 0.06310755836532862, 0.009923008709902632, 0.02529597553864849, 0.09133956307985514}, {0.007966713026278204, 0.1886577078098966, 0.34299466372301174, 0.099212573226176, 0.10793692554140506, 0.06221365090744863, 0.009840148497085846, 0.02514443500821511, 0.08621409905603349}, {0.008034167117129674, 0.18788222399425217, 0.34578485268095477, 0.10011446441573224, 0.10752114073556737, 0.0634141330804115, 0.010040423254323392, 0.025117185288695665, 0.08947335614440503}, {0.008018303421951279, 0.19187057523938092, 0.34732795362510527, 0.09899440131565669, 0.10745875059423868, 0.062336717278905884, 0.009875953460955883, 0.02479822205680625, 0.08772986762640837}, {0.007924642873786504, 0.19004116405206842, 0.357207523947613, 0.10073539325458794, 0.10832607733095932, 0.06320090652035315, 0.009959112651851247, 0.025077197409396644, 0.09714028618754514}, {0.008002042126390569, 0.19335630389517078, 0.350397467602689, 0.10131062645523324, 0.10827398658073556, 0.06260750293116625, 0.009898305552182459, 0.025162334967878567, 0.08884604169852346}, {0.008069583949244416, 0.18968941096038633, 0.3508828013007185, 0.10019193409741725, 0.10806819636153626, 0.06291166041331198, 0.010151396292056995, 0.0245574472393046, 0.0880256656291037}, {0.008044777193548198, 0.19071874542331083, 0.34518349819824923, 0.10003293035935414, 0.10840679491341945, 0.06303179317506263, 0.009773756580487531, 0.025066096118629394, 0.08638049514333253}, {0.008008945757624139, 0.19128979273195817, 0.3535311395221554, 0.10107242207803101, 0.1067530771659759, 0.06250623284074634, 0.010131152201256568, 0.02511184442574141, 0.09356094668082135}, {0.008012985787431738, 0.18915015452246503, 0.34468676483347627, 0.1002385279144674, 0.10812234249112583, 0.0620429144873776, 0.010052878254747311, 0.02550826637585103, 0.08622068921787597}, {0.008031150432725427, 0.18684233474990702, 0.3499715541771298, 0.10058963499365003, 0.11069418032901465, 0.06171204569618128, 0.010004482066447273, 0.025015488891496428, 0.0838143425577115}, {0.008058754578839889, 0.1897145186231085, 0.35182783831556236, 0.10098679044964806, 0.10819484709993826, 0.0631202757330506, 0.010008776859977994, 0.02530760454713395, 0.09166209421470194}, {0.008028007495227, 0.1910281183317579, 0.35572594754088255, 0.09888149900401179, 0.10717161457324192, 0.061786614662450855, 0.009869978443232934, 0.025184202624420227, 0.09518073659045721}, {0.007990097913139553, 0.19091873361238332, 0.3544812633369704, 0.10017470598230493, 0.10663679711448788, 0.061837975670535256, 0.010031958026277857, 0.02447341884568723, 0.09190518081070077}, {0.007985671936970811, 0.19140034393234293, 0.3457524366257519, 0.10084819200960289, 0.10588493788680484, 0.0627821719847649, 0.009911968005552116, 0.024815263662744353, 0.08960230503840151}});

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
    model.func("gpm1").setEntry("s1selection", "col7", "0.01");
    model.func("gpm1").setEntry("s2selection", "col7", "1.0E-4");
    model.func("gpm1").setEntry("lboundselection", "col7", "0.0095");
    model.func("gpm1").setEntry("uboundselection", "col7", "0.0105");
    model.func("gpm1").setEntry("lcdfselection", "col7", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col7", "manual");
    model.func("gpm1").setEntry("distributionselection", "col8", "normal");
    model.func("gpm1").setEntry("s1selection", "col8", "0.025");
    model.func("gpm1").setEntry("s2selection", "col8", "2.5E-4");
    model.func("gpm1").setEntry("lboundselection", "col8", "0.02375");
    model.func("gpm1").setEntry("uboundselection", "col8", "0.026250000000000002");
    model.func("gpm1").setEntry("lcdfselection", "col8", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col8", "manual");
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
    model.func("gpm1").setEntry("args", "col7", "fr1");
    model.func("gpm1").setEntry("unit", "col7", "m");
    model.func("gpm1").setEntry("columnType", "col8", "arg");
    model.func("gpm1").setEntry("args", "col8", "r1");
    model.func("gpm1").setEntry("unit", "col8", "m");
    model.func("gpm1").setEntry("funcs", "col9", "gpm1_1");

    model.study("std4").feature("uq").set("computeaction", "append");
    model.study("std4").feature("uq").set("tablegraphgrp", "new");
    model.study("std4").label("\u7814\u7a76 4\uff0c\u4f20\u64ad");
    model.study("std4").feature("uq").set("selected", new String[]{"0", "1", "3", "5", "6", "7"});
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
    model.study("std4").feature("uq").remove("sourceType", new int[]{0, 1, 3, 5, 6, 7});
    model.study("std4").feature("uq").remove("paramDescription", new int[]{0, 1, 3, 5, 6, 7});
    model.study("std4").feature("uq").remove("pname", new int[]{0, 1, 3, 5, 6, 7});
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
         .addRows(new double[][]{{0.3517193049276792, 0.10686206001429008, 0.09303233385255259}, {0.3484473017073407, 0.10855925422332528, 0.08757034517000571}, {0.35617703042933946, 0.10707186384769125, 0.09614590822837452}, {0.35544603496383675, 0.10817836195086715, 0.09350831191967611}, {0.35089346728972526, 0.10767002976712042, 0.09095219158127467}, {0.35030511155306193, 0.10883474239930621, 0.08846989853151975}, {0.34875753745010407, 0.10779691778289444, 0.08912983733349092}, {0.34753767325408286, 0.10719267935489925, 0.08927556661286072}, {0.3538907400693961, 0.10620641148263481, 0.09596522986523162}, {0.35067805437411276, 0.1051950798583593, 0.09532664492185512}, {0.34654736512378714, 0.10801088465332824, 0.08709975169031219}, {0.3529414819735719, 0.11021853176295356, 0.08812672847425018}, {0.34967552758596115, 0.10655655060248807, 0.09200614158929778}, {0.34555718835459504, 0.1089342055225433, 0.08482243386280486}, {0.35330295325574484, 0.10761804865222381, 0.09292582218491266}, {0.3518371526225223, 0.10822761227437562, 0.09067444161384865}, {0.3423683373915222, 0.10746315162584537, 0.08496429152496851}, {0.3495366623607657, 0.10726979323381244, 0.09060933529564558}, {0.3443383471137813, 0.10663485699740795, 0.08778006761696559}, {0.3479123244250517, 0.10576477605061921, 0.09209541682447457}, {0.36649141747750646, 0.11256522108237699, 0.09423126442617483}, {0.36649141747750646, 0.10969515650156102, 0.09941223904057084}, {0.3335085825223492, 0.1024347789175787, 0.08702708415948192}, {0.36649141747750646, 0.1024347789175787, 0.11439651686347331}, {0.36183982281687327, 0.11142387020200623, 0.09269877940481487}, {0.3335085825223492, 0.11256522108237699, 0.07122977202855345}, {0.36649141747750646, 0.10393530906598472, 0.11103605722988955}, {0.33951661503656994, 0.11256522108237699, 0.07512288478588977}, {0.359248823012827, 0.10306058233022841, 0.10666695388707857}, {0.33418410686433087, 0.10427829876144278, 0.08432030976809565}, {0.3631502989692736, 0.1053873547001235, 0.10514263086631083}, {0.3365047276755924, 0.11082732025950694, 0.07563456079739084}, {0.34004961111999127, 0.1024347789175787, 0.09210401891973802}, {0.3335085825223492, 0.10961271524699084, 0.07538442101679664}, {0.3596148411990596, 0.10967585522315722, 0.09404147865927856}, {0.34312139221909954, 0.11181844051911911, 0.07863353393532911}, {0.3368497010307245, 0.10538728475300915, 0.08438485744433467}, {0.36096131312749774, 0.10449299118334944, 0.1051074413454629}, {0.34031196131921937, 0.11115402605124577, 0.07770064214275468}, {0.3615979391549508, 0.10714430416336963, 0.10036902625054882}});

    model.func("gpm2").set("source", "resultTable");
    model.func("gpm2").set("resultTable", "tbl17");
    model.func("gpm2").set("ignorenaninf", true);

    model.study("std5").feature("uq").set("designtable", "tbl17");

    model.func("gpm2").setEntry("funcs", "col3", "gpm2_1");

    model.study("std5").feature("uq").set("computeaction", "append");
    model.study("std5").feature("uq").set("tablegraphgrp", "new");
    model.study("std5").label("\u7814\u7a76 5\uff0c\u53ef\u9760\u6027");
    model.study("std5").feature("uq").setIndex("threshold", 0.1, 0);
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

    model.title("\u652f\u67b6\u7684\u4e0d\u786e\u5b9a\u6027\u91cf\u5316 - \u5706\u89d2\u7248\u672c");

    model
         .description("\u672c\u6a21\u578b\u662f\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u652f\u67b6\u6559\u5b66\u6a21\u578b\u7684\u4e00\u4e2a\u7248\u672c\uff0c\u91c7\u7528\u5b8c\u5168\u53c2\u6570\u5316\u7684\u51e0\u4f55\u7ed3\u6784\uff0c\u5e76\u4f7f\u7528\u4e86\u201c\u8bbe\u8ba1\u6a21\u5757\u201d\uff0c\u901a\u8fc7\u8fd0\u884c\u4e00\u7cfb\u5217\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u7814\u7a76\uff0c\u8bc4\u4f30\u54ea\u4e9b\u51e0\u4f55\u53c2\u6570\u5bf9\u9500\u5b54\u89d2\u5ea6\u7684\u5f71\u54cd\u6700\u5927\uff0c\u5e76\u8ba1\u7b97\u9500\u5b54\u89d2\u5ea6\u8d85\u8fc7 0.1\u00a0\u5ea6\u7684\u6982\u7387\u3002");

    return model;
  }

  public static Model run3(Model model) {

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

    model.label("bracket_uncertainty_quantification_fillet.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
