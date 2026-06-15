/*
 * gear_train.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:26 by COMSOL 6.3.0.290. */
public class gear_train {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials,_Transmission");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n_pn", "20", "\u9f7f\u6570\uff0c\u5c0f\u9f7f\u8f6e");
    model.param().set("dp_pn", "50[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u5c0f\u9f7f\u8f6e");
    model.param().set("n_wh", "30", "\u9f7f\u6570\uff0c\u5927\u9f7f\u8f6e");
    model.param().set("dp_wh", "75[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u5927\u9f7f\u8f6e");
    model.param().set("alpha", "25[deg]", "\u538b\u529b\u89d2");
    model.param().set("wg", "10[mm]", "\u9f7f\u8f6e\u5bbd\u5ea6");
    model.param().set("gr", "n_wh/n_pn", "\u9f7f\u8f6e\u6bd4");
    model.param().set("theta", "0[deg]", "\u5c0f\u9f7f\u8f6e\u65cb\u8f6c");
    model.param().set("twist", "0.5[deg]", "\u5927\u9f7f\u8f6e\u626d\u8f6c\u89d2\u5ea6");
    model.param().set("omega", "600[rad/s]", "\u4f20\u52a8\u8f74\u89d2\u901f\u5ea6");
    model.param().set("T_ext", "100[N*m]", "\u5916\u90e8\u626d\u77e9");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module\\2D\\External_Gears\\spur_gear_2d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n", "n_pn");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dp", "dp_pn");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "alpha", "alpha");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "n", "n_wh");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dp", "dp_wh");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "alpha", "alpha");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "xc", "(dp_pn+dp_wh)/2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "th", "360/(2*n_wh)[deg]");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);

    model.component("comp1").pair().create("p1", "Contact");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("p1").source().named("geom1_pi1_sel3");
    model.component("comp1").pair("p1").destination().named("geom1_pi2_sel3");

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

    model.component("comp1").physics("mbd").prop("d").set("d", "wg");
    model.component("comp1").physics("mbd").create("cnt1", "SolidContact", 1);
    model.component("comp1").physics("mbd").feature("cnt1").set("pairs", new String[]{"p1"});
    model.component("comp1").physics("mbd").feature("cnt1").set("ContactMethodCtrl", "Nitsche");
    model.component("comp1").physics("mbd").create("att1", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att1").selection().set(52, 53, 64, 65);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("thp", "theta");
    model.component("comp1").physics("mbd").create("att2", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att2").selection().set(246, 247, 257, 262);
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("hgj2").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("thp", "-theta/gr+twist");
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("WeakConstraints", true);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("T", "mbd.hgj2.pm1.RM");
    model.component("comp1").variable("var1").descr("T", "\u6240\u9700\u626d\u77e9");
    model.component("comp1").variable("var1").set("kt", "T/twist");
    model.component("comp1").variable("var1").descr("kt", "\u6297\u626d\u521a\u5ea6");
    model.component("comp1").variable("var1").set("kg", "kt/(dp_wh/2*cos(alpha))^2");
    model.component("comp1").variable("var1").descr("kg", "\u6cbf\u4f5c\u7528\u7ebf\u7684\u521a\u5ea6");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "n_pn", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "n_pn", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "theta", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,1,40)", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 8);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbd.mises");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"kg"});
    model.result().numerical("gev1").set("descr", new String[]{"\u6cbf\u4f5c\u7528\u7ebf\u7684\u521a\u5ea6"});
    model.result().numerical("gev1").set("unit", new String[]{"N/m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"kg"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u6cbf\u4f5c\u7528\u7ebf\u7684\u521a\u5ea6"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"N/m"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "theta");
    model.result("pg3").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
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
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("maxframes", 40);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "gear_train.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").feature("fin").set("action", "assembly");
    model.component("comp2").geom("geom2").feature("fin").set("createpairs", false);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").physics().create("mbd2", "MultibodyDynamics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/mbd2", false);

    model.component("comp2").view("view4").set("renderwireframe", true);

    model.component("comp2").func().create("int1", "Interpolation");
    model.component("comp2").func("int1").set("source", "resultTable");
    model.component("comp2").func("int1").setEntry("funcnames", "col2", "gearStiffness");
    model.component("comp2").func().create("step1", "Step");
    model.component("comp2").func("step1").set("location", "0.5[ms]");
    model.component("comp2").func("step1").set("smooth", "1e-3");

    model.component("comp2").selection().create("sel1", "Explicit");
    model.component("comp2").selection("sel1").geom(2);
    model.component("comp2").selection("sel1").set(71, 72, 75, 76);
    model.component("comp2").selection("sel1").set("groupcontang", true);
    model.component("comp2").selection().duplicate("sel2", "sel1");
    model.component("comp2").selection("sel2").remove(71, 72, 75, 76);
    model.component("comp2").selection("sel2").add(69, 70, 73, 74);
    model.component("comp2").selection().duplicate("sel3", "sel2");
    model.component("comp2").selection("sel3").remove(69, 70, 73, 74);
    model.component("comp2").selection("sel3").add(79, 80, 82, 84);
    model.component("comp2").selection().duplicate("sel4", "sel3");
    model.component("comp2").selection("sel4").remove(79, 80, 82, 84);
    model.component("comp2").selection("sel4").add(77, 78, 81, 83);
    model.component("comp2").selection().duplicate("sel5", "sel4");
    model.component("comp2").selection("sel5").remove(77, 78, 81, 83);
    model.component("comp2").selection("sel5").add(87, 88, 90, 92);
    model.component("comp2").selection().duplicate("sel6", "sel5");
    model.component("comp2").selection("sel6").remove(87, 88, 90, 92);
    model.component("comp2").selection("sel6").add(85, 86, 89, 91);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp2").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp2").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp2").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp2").material("mat2").label("Structural steel");
    model.component("comp2").material("mat2").set("family", "custom");
    model.component("comp2").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat2").set("diffuse", "custom");
    model.component("comp2").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("ambient", "custom");
    model.component("comp2").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("noise", true);
    model.component("comp2").material("mat2").set("fresnel", 0.9);
    model.component("comp2").material("mat2").set("roughness", 0.3);
    model.component("comp2").material("mat2").set("diffusewrap", 0);
    model.component("comp2").material("mat2").set("reflectance", 0);
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp2").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp2").physics("mbd2").create("spg1", "SpurGear", 3);
    model.component("comp2").physics("mbd2").feature("spg1").selection().set(27, 28);
    model.component("comp2").physics("mbd2").feature("spg1").set("nt", "n_pn");
    model.component("comp2").physics("mbd2").feature("spg1").set("dp", "dp_pn");
    model.component("comp2").physics("mbd2").feature("spg1").set("alpha", "alpha");
    model.component("comp2").physics("mbd2").feature("spg1").set("eg", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").feature("spg1").set("InitialValueType", "locallyDefined");
    model.component("comp2").physics("mbd2").feature("spg1").feature("init1")
         .set("omega", new String[]{"0", "omega", "0"});
    model.component("comp2").physics("mbd2").create("spg2", "SpurGear", 3);
    model.component("comp2").physics("mbd2").feature("spg2").selection().set(29, 30);
    model.component("comp2").physics("mbd2").feature("spg2").set("nt", "n_wh");
    model.component("comp2").physics("mbd2").feature("spg2").set("dp", "dp_wh");
    model.component("comp2").physics("mbd2").feature("spg2").set("alpha", "alpha");
    model.component("comp2").physics("mbd2").feature("spg2").set("eg", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").feature("spg2").set("InitialValueType", "locallyDefined");
    model.component("comp2").physics("mbd2").feature("spg2").feature("init1")
         .set("omega", new String[]{"0", "-omega/gr", "0"});
    model.component("comp2").physics("mbd2").create("spg3", "SpurGear", 3);
    model.component("comp2").physics("mbd2").feature("spg3").selection().set(31);
    model.component("comp2").physics("mbd2").feature("spg3").set("nt", "n_pn");
    model.component("comp2").physics("mbd2").feature("spg3").set("dp", "dp_pn");
    model.component("comp2").physics("mbd2").feature("spg3").set("alpha", "alpha");
    model.component("comp2").physics("mbd2").feature("spg3").set("eg", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").feature("spg3").set("InitialValueType", "locallyDefined");
    model.component("comp2").physics("mbd2").feature("spg3").feature("init1")
         .set("omega", new String[]{"0", "-omega/gr", "0"});
    model.component("comp2").physics("mbd2").create("spg4", "SpurGear", 3);
    model.component("comp2").physics("mbd2").feature("spg4").selection().set(32, 33);
    model.component("comp2").physics("mbd2").feature("spg4").set("nt", "n_wh");
    model.component("comp2").physics("mbd2").feature("spg4").set("dp", "dp_wh");
    model.component("comp2").physics("mbd2").feature("spg4").set("alpha", "alpha");
    model.component("comp2").physics("mbd2").feature("spg4").set("eg", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").feature("spg4").set("InitialValueType", "locallyDefined");
    model.component("comp2").physics("mbd2").feature("spg4").feature("init1")
         .set("omega", new String[]{"0", "omega/gr^2", "0"});
    model.component("comp2").physics("mbd2").create("grp1", "GearPair", -1);
    model.component("comp2").physics("mbd2").feature("grp1").set("Wheel", "spg1");
    model.component("comp2").physics("mbd2").feature("grp1").set("Pinion", "spg2");
    model.component("comp2").physics("mbd2").feature("grp1").set("IncludeGearElasticity", true);
    model.component("comp2").physics("mbd2").feature("grp1").set("ContactForceComputation", "WeakConstraints");
    model.component("comp2").physics("mbd2").feature("grp1").feature("gel1").set("Specify", "GearPair");
    model.component("comp2").physics("mbd2").feature("grp1").feature("gel1")
         .set("km_dr", "gearStiffness(mbd2.grp1.thm_wh*180/pi)");
    model.component("comp2").physics("mbd2").create("grp2", "GearPair", -1);
    model.component("comp2").physics("mbd2").feature("grp2").set("Wheel", "spg3");
    model.component("comp2").physics("mbd2").feature("grp2").set("Pinion", "spg4");
    model.component("comp2").physics("mbd2").feature("grp2").set("ObtainedThrough", "CounterclockwiseDirection");
    model.component("comp2").physics("mbd2").feature("grp2").set("IncludeGearElasticity", true);
    model.component("comp2").physics("mbd2").feature("grp2").set("ContactForceComputation", "WeakConstraints");
    model.component("comp2").physics("mbd2").feature("grp2").feature("gel1").set("Specify", "GearPair");
    model.component("comp2").physics("mbd2").feature("grp2").feature("gel1")
         .set("km_dr", "gearStiffness(mbd2.grp2.thm_wh*180/pi)");
    model.component("comp2").physics("mbd2").create("fxj1", "FixedJoint", -1);
    model.component("comp2").physics("mbd2").feature("fxj1").set("Source", "spg2");
    model.component("comp2").physics("mbd2").feature("fxj1").set("Destination", "spg3");
    model.component("comp2").physics("mbd2").create("att1", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att1").selection().named("sel1");
    model.component("comp2").physics("mbd2").create("att2", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att2").selection().named("sel2");
    model.component("comp2").physics("mbd2").create("att3", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att3").selection().named("sel3");
    model.component("comp2").physics("mbd2").create("att4", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att4").selection().named("sel4");
    model.component("comp2").physics("mbd2").create("att5", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att5").selection().named("sel5");
    model.component("comp2").physics("mbd2").create("att6", "Attachment", 2);
    model.component("comp2").physics("mbd2").feature("att6").selection().named("sel6");
    model.component("comp2").physics("mbd2").create("hgj1", "HingeJoint", -1);
    model.component("comp2").physics("mbd2").feature("hgj1").set("Source", "att1");
    model.component("comp2").physics("mbd2").feature("hgj1").set("Destination", "spg1");
    model.component("comp2").physics("mbd2").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").create("hgj2", "HingeJoint", -1);
    model.component("comp2").physics("mbd2").feature("hgj2").set("Source", "att2");
    model.component("comp2").physics("mbd2").feature("hgj2").set("Destination", "spg1");
    model.component("comp2").physics("mbd2").feature("hgj2").set("e", new int[]{0, 1, 0});
    model.component("comp2").physics("mbd2").feature().duplicate("hgj3", "hgj2");
    model.component("comp2").physics("mbd2").feature("hgj3").set("Source", "att3");
    model.component("comp2").physics("mbd2").feature("hgj3").set("Destination", "spg2");
    model.component("comp2").physics("mbd2").feature().duplicate("hgj4", "hgj3");
    model.component("comp2").physics("mbd2").feature("hgj4").set("Source", "att4");
    model.component("comp2").physics("mbd2").feature().duplicate("hgj5", "hgj4");
    model.component("comp2").physics("mbd2").feature("hgj5").set("Source", "att5");
    model.component("comp2").physics("mbd2").feature("hgj5").set("Destination", "spg4");
    model.component("comp2").physics("mbd2").feature().duplicate("hgj6", "hgj5");
    model.component("comp2").physics("mbd2").feature("hgj6").set("Source", "att6");
    model.component("comp2").physics("mbd2").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp2").physics("mbd2").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp2").physics("mbd2").feature("hgj1").feature("pm1").set("omegap", "omega");
    model.component("comp2").physics("mbd2").feature("hgj5").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp2").physics("mbd2").feature("hgj5").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp2").physics("mbd2").feature("hgj5").feature("afm1").set("Ms", "-T_ext*step1(t)");
    model.component("comp2").physics("mbd2").create("fix1", "Fixed", 2);
    model.component("comp2").physics("mbd2").feature("fix1").selection().set(67, 68, 93, 94);

    model.component("comp2").mesh("mesh2").autoMeshSize(4);
    model.component("comp2").mesh("mesh2").contribute("geom/detail", false);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", false);
    model.study("std2").feature("time").setSolveFor("/physics/mbd2", true);
    model.study("std2").feature("time").set("tlist", "range(0,3.5e-5,7e-3)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-6");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4f4d\u79fb (mbd2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (mbd2)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg5").feature("vol1").set("descractive", true);
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg5").feature("vol1").set("rangecoloractive", "on");
    model.result("pg5").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg5").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("colortabletype", "discrete");
    model.result("pg5").feature("vol1").set("smooth", "none");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg5").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd2.u_tX", "mbd2.u_tY", "mbd2.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg5").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg4").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().geom("geom2", 3);
    model.result().dataset("dset4").selection().set(27, 28, 29, 30, 31, 32, 33);
    model.result().dataset().duplicate("dset5", "dset3");
    model.result().dataset("dset5").selection().geom("geom2", 3);
    model.result().dataset("dset5").selection().geom("geom2", 3);
    model.result().dataset("dset5").selection().set(13, 16, 22);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 131, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "dset4");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset5");
    model.result("pg4").feature("surf2").set("expr", "mbd2.an");
    model.result("pg4").feature("surf2").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf2").set("rangecoloractive", true);
    model.result("pg4").feature("surf2").set("rangecolormin", -15);
    model.result("pg4").feature("surf2").set("rangecolormax", 15);
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb-\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u63a5\u89e6\u529b");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd2.grp1.Fc"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u63a5\u89e6\u70b9\u7684\u529b"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd2.grp1.Fc", "mbd2.grp2.Fc"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u63a5\u89e6\u70b9\u7684\u529b", "\u63a5\u89e6\u70b9\u7684\u529b"});
    model.result("pg6").feature("glob1").setIndex("expr", "-mbd2.grp2.Fc", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u63a5\u89e6\u70b9\u7684\u529b", 1);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "mbd2.hgj1.th");
    model.result("pg6").feature("glob1").set("xdatadescr", "\u76f8\u5bf9\u65cb\u8f6c");
    model.result("pg6").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg6").feature("glob1").set("xdatadescractive", true);
    model.result("pg6").feature("glob1").set("xdatadescr", "\u4f20\u52a8\u8f74\u65cb\u8f6c");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "Gear pair 1", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "\u9f7f\u8f6e\u526f 1", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "Gear pair 2", 1);
    model.result("pg6").feature("glob1").setIndex("legends", "\u9f7f\u8f6e\u526f 2", 1);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u89d2\u901f\u5ea6");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg7").set("legendpos", "upperright");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd2.hgj1.th_t"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg7").feature("glob1").setIndex("expr", "-mbd2.hgj3.th_t", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd2.hgj5.th_t", 2);
    model.result("pg7").feature("glob1").setIndex("legends", "\u4f20\u52a8\u8f74", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "\u4e2d\u95f4\u8f74", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "\u8f93\u51fa\u8f74", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u89d2\u52a0\u901f\u5ea6");
    model.result("pg8").set("ylabel", "\u89d2\u52a0\u901f\u5ea6 (rad/s^2)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "mbd2.hgj1.th_tt", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "-mbd2.hgj3.th_tt", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd2.hgj5.th_tt", 2);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u8fde\u63a5\u4ef6 5 \u7684\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "mbd2.att5.u_tty", 0);
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "mbd2.hgj1.th");
    model.result("pg9").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg9").feature("glob1").set("xdatadescractive", true);
    model.result("pg9").feature("glob1").set("xdatadescr", "\u4f20\u52a8\u8f74\u65cb\u8f6c");
    model.result("pg9").feature("glob1").set("linewidth", 2);
    model.result("pg9").feature("glob1").set("legend", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6cd5\u5411\u52a0\u901f\u5ea6\uff1a\u9891\u7387");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").set("xdata", "fourier");
    model.result("pg10").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg10").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("fontsize", "9");
    model.result().export("anim2").set("colortheme", "globaltheme");
    model.result().export("anim2").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim2").set("background", "color");
    model.result().export("anim2").set("gltfincludelines", "on");
    model.result().export("anim2").set("title1d", "on");
    model.result().export("anim2").set("legend1d", "on");
    model.result().export("anim2").set("logo1d", "on");
    model.result().export("anim2").set("options1d", "on");
    model.result().export("anim2").set("title2d", "on");
    model.result().export("anim2").set("legend2d", "on");
    model.result().export("anim2").set("logo2d", "on");
    model.result().export("anim2").set("options2d", "off");
    model.result().export("anim2").set("title3d", "on");
    model.result().export("anim2").set("legend3d", "on");
    model.result().export("anim2").set("logo3d", "on");
    model.result().export("anim2").set("options3d", "off");
    model.result().export("anim2").set("axisorientation", "on");
    model.result().export("anim2").set("grid", "on");
    model.result().export("anim2").set("axes1d", "on");
    model.result().export("anim2").set("axes2d", "on");
    model.result().export("anim2").set("showgrid", "on");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg4");
    model.result().export("anim2").set("maxframes", 50);

    model.nodeGroup().create("grp1", "Physics", "mbd2");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("spg1");
    model.nodeGroup("grp1").add("spg2");
    model.nodeGroup("grp1").add("spg3");
    model.nodeGroup("grp1").add("spg4");
    model.nodeGroup("grp1").label("\u9f7f\u8f6e");
    model.nodeGroup().create("grp2", "Physics", "mbd2");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("grp1");
    model.nodeGroup("grp2").add("grp2");
    model.nodeGroup("grp2").label("\u9f7f\u8f6e\u526f");
    model.nodeGroup().create("grp3", "Physics", "mbd2");
    model.nodeGroup("grp3").placeAfter("fxj1");
    model.nodeGroup("grp3").add("att1");
    model.nodeGroup("grp3").add("att2");
    model.nodeGroup("grp3").add("att3");
    model.nodeGroup("grp3").add("att4");
    model.nodeGroup("grp3").add("att5");
    model.nodeGroup("grp3").add("att6");
    model.nodeGroup("grp3").label("\u8fde\u63a5\u4ef6");

    return model;
  }

  public static Model run3(Model model) {
    model.nodeGroup().create("grp4", "Physics", "mbd2");
    model.nodeGroup("grp4").placeAfter("fxj1");
    model.nodeGroup("grp4").add("hgj1");
    model.nodeGroup("grp4").add("hgj2");
    model.nodeGroup("grp4").add("hgj3");
    model.nodeGroup("grp4").add("hgj4");
    model.nodeGroup("grp4").add("hgj5");
    model.nodeGroup("grp4").add("hgj6");
    model.nodeGroup("grp4").label("\u94f0\u94fe\u5173\u8282");

    model.result("pg4").run();

    model.title("\u590d\u5408\u8f6e\u7cfb\u7684\u632f\u52a8");

    model
         .description("\u672c\u4f8b\u9610\u660e\u76f4\u9f7f\u8f6e\u7cfb\u7684\u5efa\u6a21\u3002\u6267\u884c\u77ac\u6001\u5206\u6790\u4ee5\u8ba1\u7b97\u6240\u6709\u9f7f\u8f6e\u7684\u89d2\u901f\u5ea6\u4ee5\u53ca\u6240\u6709\u9f7f\u8f6e\u627f\u53d7\u7684\u529b\u548c\u529b\u77e9\u3002\u5047\u8bbe\u9f7f\u8f6e\u556e\u5408\u4e3a\u5f39\u6027\u556e\u5408\uff0c\u5e76\u4e14\u4f7f\u7528\u63a5\u89e6\u5efa\u6a21\u6765\u8ba1\u7b97\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6\u3002\u6267\u884c\u53c2\u6570\u5316\u5206\u6790\u4ee5\u8ba1\u7b97\u4e00\u4e2a\u556e\u5408\u5468\u671f\u4e2d\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6\u968f\u9f7f\u8f6e\u65cb\u8f6c\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("gear_train.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
