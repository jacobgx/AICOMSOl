/*
 * gear_train_noise.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:26 by COMSOL 6.3.0.290. */
public class gear_train_noise {

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
    model.param().set("n_pn", "20", "Number of teeth, pinion");
    model.param().set("dp_pn", "50[mm]", "Pitch diameter, pinion");
    model.param().set("n_wh", "30", "Number of teeth, wheel");
    model.param().set("dp_wh", "75[mm]", "Pitch diameter, wheel");
    model.param().set("alpha", "25[deg]", "Pressure angle");
    model.param().set("wg", "10[mm]", "Gear width");
    model.param().set("gr", "n_wh/n_pn", "Gear ratio");
    model.param().set("theta", "0[deg]", "Pinion rotation");
    model.param().set("twist", "0.5[deg]", "Wheel twisting");
    model.param().set("omega", "600[rad/s]", "Angular velocity of drive shaft");
    model.param().set("T_ext", "100[N*m]", "External torque");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module/2D/External_Gears/spur_gear_2d.mph", new String[]{"part1"});
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
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
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
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").label("Hockett-Sherby");
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
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
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
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
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
    model.component("comp1").variable("var1").descr("T", "Required twisting moment");
    model.component("comp1").variable("var1").set("kt", "T/twist");
    model.component("comp1").variable("var1").descr("kt", "Torsional stiffness");
    model.component("comp1").variable("var1").set("kg", "kt/(dp_wh/2*cos(alpha))^2");
    model.component("comp1").variable("var1").descr("kg", "Stiffness along the line of action");

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
    model.result("pg1").label("Displacement (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Surface");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("Deformation");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Stress");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbd.mises");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"kg"});
    model.result().numerical("gev1").set("descr", new String[]{"Stiffness along the line of action"});
    model.result().numerical("gev1").set("unit", new String[]{"N/m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Gear Mesh Stiffness");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"kg"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"Stiffness along the line of action"});
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
    model.component("comp2").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
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
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
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
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").label("Hockett-Sherby");
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
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
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
    model.component("comp2").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
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
    model.result("pg4").label("Displacement (mbd2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Surface");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("def1", "Deform");
    model.result("pg4").feature("surf1").feature("def1").label("Deformation");
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Velocity (mbd2)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").label("Domain Numbers");
    model.result("pg5").feature("vol1").set("descractive", true);
    model.result("pg5").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg5").feature("vol1").set("descr", "Domain numbers");
    model.result("pg5").feature("vol1").set("rangecoloractive", "on");
    model.result("pg5").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg5").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg5").feature("vol1").set("colortable", "Cyclic");
    model.result("pg5").feature("vol1").set("colorlegend", false);
    model.result("pg5").feature("vol1").set("colortabletype", "discrete");
    model.result("pg5").feature("vol1").set("smooth", "none");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").feature("vol1").feature().create("def1", "Deform");
    model.result("pg5").feature("vol1").feature("def1").label("Deformation");
    model.result("pg5").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature().create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").label("Arrow Line");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"mbd2.u_tX", "mbd2.u_tY", "mbd2.u_tZ"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("data", "parent");
    model.result("pg5").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg5").feature("arwl1").feature("def1").label("Deformation");
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
    model.result("pg4").label("Displacement-Normal Acceleration");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Contact force");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd2.grp1.Fc"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"Force at contact point"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd2.grp1.Fc", "mbd2.grp2.Fc"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"Force at contact point", "Force at contact point"});
    model.result("pg6").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "-mbd2.grp2.Fc", 1);
    model.result("pg6").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "Force at contact point", 1);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "mbd2.hgj1.th");
    model.result("pg6").feature("glob1").set("xdatadescr", "Relative rotation");
    model.result("pg6").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg6").feature("glob1").set("xdatadescractive", true);
    model.result("pg6").feature("glob1").set("xdatadescr", "Drive shaft rotation");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "Gear pair 1", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "Gear pair 2", 1);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("Angular Velocity");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "Angular velocity (rad/s)");
    model.result("pg7").set("legendpos", "upperright");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd2.hgj1.th_t"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"Relative angular velocity"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg7").feature("glob1").setIndex("expr", "-mbd2.hgj3.th_t", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd2.hgj5.th_t", 2);
    model.result("pg7").feature("glob1").setIndex("legends", "Drive shaft", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "Intermediate shaft", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "Output shaft", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("Angular Acceleration");
    model.result("pg8").set("ylabel", "Angular acceleration (rad/s^2)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "mbd2.hgj1.th_tt", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "-mbd2.hgj3.th_tt", 1);
    model.result("pg8").feature("glob1").setIndex("expr", "mbd2.hgj5.th_tt", 2);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("Normal Acceleration");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "Normal acceleration at Attachment-5");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "mbd2.att5.u_tty", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "m/s^2", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "Rigid body acceleration, y-component", 0);
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "mbd2.hgj1.th");
    model.result("pg9").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg9").feature("glob1").set("xdatadescractive", true);
    model.result("pg9").feature("glob1").set("xdatadescr", "Drive shaft rotation");
    model.result("pg9").feature("glob1").set("linewidth", 2);
    model.result("pg9").feature("glob1").set("legend", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("Normal Acceleration: Frequency");
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
    model.nodeGroup("grp1").label("Gears");
    model.nodeGroup().create("grp2", "Physics", "mbd2");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("grp1");
    model.nodeGroup("grp2").add("grp2");
    model.nodeGroup("grp2").label("Gear Pairs");
    model.nodeGroup().create("grp3", "Physics", "mbd2");
    model.nodeGroup("grp3").placeAfter("fxj1");
    model.nodeGroup("grp3").add("att1");
    model.nodeGroup("grp3").add("att2");
    model.nodeGroup("grp3").add("att3");
    model.nodeGroup("grp3").add("att4");
    model.nodeGroup("grp3").add("att5");
    model.nodeGroup("grp3").add("att6");
    model.nodeGroup("grp3").label("Attachments");
    model.nodeGroup().create("grp4", "Physics", "mbd2");
    model.nodeGroup("grp4").placeAfter("fxj1");
    model.nodeGroup("grp4").add("hgj1");
    model.nodeGroup("grp4").add("hgj2");
    model.nodeGroup("grp4").add("hgj3");
    model.nodeGroup("grp4").add("hgj4");
    model.nodeGroup("grp4").add("hgj5");
    model.nodeGroup("grp4").add("hgj6");
    model.nodeGroup("grp4").label("Hinge Joints");

    model.result("pg4").run();

    model.title("\u590d\u5408\u8f6e\u7cfb\u7684\u632f\u52a8");

    model
         .description("\u672c\u4f8b\u9610\u660e\u76f4\u9f7f\u8f6e\u7cfb\u7684\u5efa\u6a21\u3002\u6267\u884c\u77ac\u6001\u5206\u6790\u4ee5\u8ba1\u7b97\u6240\u6709\u9f7f\u8f6e\u7684\u89d2\u901f\u5ea6\u4ee5\u53ca\u6240\u6709\u9f7f\u8f6e\u627f\u53d7\u7684\u529b\u548c\u529b\u77e9\u3002\u5047\u8bbe\u9f7f\u8f6e\u556e\u5408\u4e3a\u5f39\u6027\u556e\u5408\uff0c\u5e76\u4e14\u4f7f\u7528\u63a5\u89e6\u5efa\u6a21\u6765\u8ba1\u7b97\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6\u3002\u6267\u884c\u53c2\u6570\u5316\u5206\u6790\u4ee5\u8ba1\u7b97\u4e00\u4e2a\u556e\u5408\u5468\u671f\u4e2d\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6\u968f\u9f7f\u8f6e\u65cb\u8f6c\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.label("gear_train.mph");

    model.result("pg4").run();

    model.param().set("x0", "0.07[m]");
    model.param().descr("x0", "\u7403\u4f53\u504f\u79fb\u91cf");
    model.param().set("R", "0.5[m]");
    model.param().descr("R", "\u7403\u4f53\u534a\u5f84");
    model.param().set("f0", "2000[Hz]");
    model.param().descr("f0", "\u6d4b\u8bd5\u7684\u9891\u7387");

    model.component("comp2").selection().create("sel7", "Explicit");
    model.component("comp2").selection("sel7").geom(2);
    model.component("comp2").selection("sel7")
         .set(14, 38, 46, 53, 65, 132, 219, 220, 225, 455, 456, 461, 919, 920, 925);
    model.component("comp2").selection("sel7").set("groupcontang", true);

    model.component("comp2").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp2").cpl("genext1").selection().geom("geom2", 2);
    model.component("comp2").cpl("genext1").selection().named("sel7");
    model.component("comp2").cpl("genext1").set("srcframe", "material");
    model.component("comp2").cpl("genext1").set("dstmap", new String[]{"X", "Y", "Z"});
    model.component("comp2").cpl("genext1").set("method", "closest");

    model.study("std1").label("\u7814\u7a76 1 - \u4e8c\u7ef4\u9f7f\u8f6e");
    model.study("std2").label("\u7814\u7a76 2 - \u8f6e\u7cfb");

    model.component().create("comp3", true);

    return model;
  }

  public static Model run3(Model model) {

    model.component("comp3").geom().create("geom3", 3);
    model.component("comp3").geom("geom3").geomRep("comsol");

    model.component("comp3").mesh().create("mesh3");
    model.component("comp3").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp3").geom("geom3").create("imp1", "Import");
    model.component("comp3").geom("geom3").feature("imp1").set("filename", "gear_train_noise.mphbin");
    model.component("comp3").geom("geom3").feature("imp1").importData();
    model.component("comp3").geom("geom3").create("csol1", "ConvertToSolid");

    model.component("comp3").view("view5").set("transparency", true);

    model.component("comp3").geom("geom3").feature("csol1").selection("input").set("imp1");
    model.component("comp3").geom("geom3").run("csol1");
    model.component("comp3").geom("geom3").create("sph1", "Sphere");
    model.component("comp3").geom("geom3").feature("sph1").set("r", "R");
    model.component("comp3").geom("geom3").feature("sph1").set("pos", new String[]{"x0", "0", "0"});
    model.component("comp3").geom("geom3").run("sph1");
    model.component("comp3").geom("geom3").create("dif1", "Difference");
    model.component("comp3").geom("geom3").feature("dif1").selection("input").set("sph1");
    model.component("comp3").geom("geom3").feature("dif1").selection("input2").set("csol1");
    model.component("comp3").geom("geom3").run("dif1");

    model.component("comp3").physics().create("bode", "BoundaryODE", "geom3");

    model.study("std1").feature("stat").setSolveFor("/physics/bode", false);
    model.study("std2").feature("time").setSolveFor("/physics/bode", false);

    model.component("comp3").physics("bode").prop("EquationForm").set("form", "Automatic");

    model.component("comp3").geom("geom3").run();

    model.component("comp3").physics().create("acpr", "PressureAcoustics", "geom3");

    model.study("std1").feature("stat").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("time").setSolveFor("/physics/acpr", false);

    model.component("comp3").material().create("mat3", "Common");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp3").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp3").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp3").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat3").label("Air");
    model.component("comp3").material("mat3").set("family", "air");
    model.component("comp3").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp3").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp3").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp3").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp3").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp3").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp3").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp3").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp3").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp3").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp3").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp3").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp3").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp3").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp3").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp3").material("mat3").materialType("nonSolid");

    model.component("comp3").selection().create("sel8", "Explicit");
    model.component("comp3").selection("sel8").geom(2);
    model.component("comp3").selection("sel8").set(1);
    model.component("comp3").selection("sel8").set("groupcontang", true);
    model.component("comp3").selection("sel8").label("\u5916\u8fb9\u754c");
    model.component("comp3").selection().create("box1", "Box");
    model.component("comp3").selection("box1").label("\u5916\u58f3");
    model.component("comp3").selection("box1").set("entitydim", 2);
    model.component("comp3").selection("box1").set("ymin", -0.06);
    model.component("comp3").selection("box1").set("ymax", 0.06);
    model.component("comp3").selection("box1").set("condition", "inside");

    model.component("comp3").physics("bode").selection().named("box1");
    model.component("comp3").physics("bode").prop("Units").set("DependentVariableQuantity", "acceleration");
    model.component("comp3").physics("bode").prop("Units").set("SourceTermQuantity", "length");
    model.component("comp3").physics("bode").prop("ShapeProperty").set("shapeFunctionType", "shlag");
    model.component("comp3").physics("bode").prop("ShapeProperty").set("frame", "material");
    model.component("comp3").physics("bode").field("dimensionless").field("an");
    model.component("comp3").physics("bode").field("dimensionless").component(1, "an");
    model.component("comp3").physics("bode").feature("dode1").setIndex("f", 0, 0);
    model.component("comp3").physics("bode").feature("dode1").setIndex("da", 0, 0);
    model.component("comp3").physics("bode").feature("init1").set("an", "comp2.genext1(comp2.mbd2.an)");
    model.component("comp3").physics("acpr").create("swr1", "SphericalWaveRadiation", 2);
    model.component("comp3").physics("acpr").feature("swr1").selection().named("sel8");
    model.component("comp3").physics("acpr").feature("swr1").set("r0", new String[]{"x0", "0", "0"});
    model.component("comp3").physics("acpr").create("nacc1", "NormalAcceleration", 2);
    model.component("comp3").physics("acpr").feature("nacc1").selection().named("box1");
    model.component("comp3").physics("acpr").feature("nacc1").set("nacc", "comp3.an");
    model.component("comp3").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp3").physics("acpr").feature("efc1").selection().named("sel8");
    model.component("comp3").physics("acpr").feature("efc1").set("UsePPR", false);

    model.component("comp3").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp3").mesh("mesh3").feature("size").set("custom", true);
    model.component("comp3").mesh("mesh3").feature("size").set("hmax", "343[m/s]/f0/5");
    model.component("comp3").mesh("mesh3").run();
    model.component("comp3").mesh("mesh3").create("bl1", "BndLayer");
    model.component("comp3").mesh("mesh3").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp3").mesh("mesh3").feature("bl1").selection().geom(3);
    model.component("comp3").mesh("mesh3").feature("bl1").selection().set();
    model.component("comp3").mesh("mesh3").feature("bl1").selection().allGeom();
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").selection().named("sel8");
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp3").mesh("mesh3").feature("bl1").feature("blp").set("blhmin", "343[m/s]/f0/5/10");
    model.component("comp3").mesh("mesh3").run();

    model.study().create("std3");

    model.sol("sol2").updateSolution();

    model.result("pg4").run();

    model.study("std3").label("\u7814\u7a76 3 - \u58f0\u5b66");
    model.study("std3").create("tffft", "TimeToFreqFFT");
    model.study("std3").feature("tffft").set("fftinputmethod", "init");
    model.study("std3").feature("tffft").set("fftinputstudy", "std2");
    model.study("std3").feature("tffft").set("fftendtime", "7e-3");
    model.study("std3").feature("tffft").set("fftmaxfreq", "1/(7e-5)");
    model.study("std3").feature("tffft").setSolveFor("/physics/mbd", false);
    model.study("std3").feature("tffft").setSolveFor("/physics/mbd2", false);
    model.study("std3").feature("tffft").setSolveFor("/physics/acpr", false);
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").set("plist", "f0");
    model.study("std3").feature("freq").setSolveFor("/physics/mbd", false);
    model.study("std3").feature("freq").setSolveFor("/physics/mbd2", false);
    model.study("std3").feature("freq").setSolveFor("/physics/bode", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std3");
    model.study("std3").feature("freq").set("notsolnum", "all");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").set("nonlin", false);

    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().duplicate("dset9", "dset8");
    model.result().dataset("dset9").selection().geom("geom3", 2);
    model.result().dataset("dset9").selection().named("box1");
    model.result().dataset().duplicate("dset10", "dset9");
    model.result().dataset("dset10").selection().geom("geom3", 2);
    model.result().dataset("dset10").selection().set();
    model.result().dataset("dset10").selection().geom("geom3", 2);
    model.result().dataset("dset10").selection()
         .set(5, 6, 7, 10, 11, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 43, 44, 45, 48, 49, 53, 54, 55, 57, 59, 67, 68, 69, 71, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96);
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset8");
    model.result().dataset("grid1").set("parmin1", -2);
    model.result().dataset("grid1").set("parmax1", 2);
    model.result().dataset("grid1").set("parmin2", -2);
    model.result().dataset("grid1").set("parmax2", 2);
    model.result().dataset("grid1").set("parmax3", 0);
    model.result().dataset("grid1").set("res1", 100);
    model.result().dataset("grid1").set("res2", 100);
    model.result().dataset("grid1").set("res3", 2);
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset9");
    model.result("pg11").label("\u5916\u58f3\u6cd5\u5411\u52a0\u901f\u5ea6\uff1a\u5927\u5c0f\u548c\u76f8\u4f4d");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "abs(acpr.nacc)");
    model.result("pg11").run();
    model.result("pg11").create("surf2", "Surface");
    model.result("pg11").feature("surf2").set("expr", "arg(acpr.nacc)");
    model.result("pg11").feature("surf2").create("trn1", "Transformation");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").feature("trn1").set("move", new double[]{0.4, 0, 0});
    model.result("pg11").run();
    model.result("pg11").feature("surf2").set("colortable", "Disco");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "freq(1)=2000 Hz \u8868\u9762\uff1a\u5927\u5c0f (m/s^2) \u8868\u9762\uff1a\u76f8\u4f4d (rad)");
    model.result("pg11").set("legendpos", "rightdouble");
    model.result("pg11").set("edges", false);
    model.result("pg11").run();

    model.component("comp3").view("view5").set("transparency", false);

    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u8fd1\u573a\u58f0\u538b\u7ea7");
    model.result("pg12").set("data", "dset8");
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "\u58f0\u538b\u7ea7 - \u8fd1\u573a");
    model.result("pg12").set("showlegendsmaxmin", true);
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "acpr.Lp_t");
    model.result("pg12").feature("slc1").set("quickxnumber", 1);
    model.result("pg12").feature("slc1").set("colortable", "Rainbow");
    model.result("pg12").feature("slc1").set("colorscalemode", "linear");
    model.result("pg12").feature().duplicate("slc2", "slc1");
    model.result("pg12").run();
    model.result("pg12").feature("slc2").set("quickplane", "xy");
    model.result("pg12").feature("slc2").set("quickznumber", 1);
    model.result("pg12").feature("slc2").set("inheritplot", "slc1");
    model.result("pg12").feature().duplicate("slc3", "slc2");
    model.result("pg12").run();
    model.result("pg12").feature("slc3").set("quickplane", "zx");
    model.result("pg12").feature("slc3").set("quickynumber", 1);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u58f3\u4f53\u8868\u9762\u7684\u58f0\u538b\u7ea7");
    model.result("pg13").set("data", "dset9");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("title", "\u58f0\u538b\u7ea7 - \u58f3\u4f53\u8868\u9762");
    model.result("pg13").set("showlegendsmaxmin", true);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg13").run();
    model.result().create("pg14", "PolarGroup");
    model.result("pg14").run();
    model.result("pg14").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 xy \u5e73\u9762");
    model.result("pg14").set("data", "dset8");
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08xy \u5e73\u9762\uff09");
    model.result("pg14").create("rp1", "RadiationPattern");
    model.result("pg14").feature("rp1").set("markerpos", "datapoints");
    model.result("pg14").feature("rp1").set("linewidth", "preference");
    model.result("pg14").feature("rp1").set("phidisc", 180);
    model.result("pg14").feature("rp1").set("center3", new String[]{"x0", "0", "0"});
    model.result("pg14").feature("rp1").set("radius", "4*R");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 xz \u5e73\u9762");
    model.result("pg15").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08xz \u5e73\u9762\uff09");
    model.result("pg15").run();
    model.result("pg15").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg15").run();
    model.result("pg15").run();
    model.result().duplicate("pg16", "pg15");
    model.result("pg16").run();
    model.result("pg16").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 yz \u5e73\u9762");
    model.result("pg16").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08yz \u5e73\u9762\uff09");
    model.result("pg16").run();
    model.result("pg16").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").label("\u4e09\u7ef4\u6781\u5750\u6807\u58f0\u538b\u7ea7");
    model.result("pg17").set("data", "dset8");
    model.result("pg17").set("titletype", "manual");
    model.result("pg17").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08\u4e09\u7ef4\uff09");
    model.result("pg17").create("rp1", "RadiationPattern");
    model.result("pg17").feature("rp1").set("thetadisc", 50);
    model.result("pg17").feature("rp1").set("phidisc", 50);
    model.result("pg17").feature("rp1").set("sphere", "manual");
    model.result("pg17").feature("rp1").set("center", new String[]{"x0", "0", "0"});
    model.result("pg17").feature("rp1").set("radius", "4*R");
    model.result("pg17").feature("rp1").set("grid", "fine");
    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").run();
    model.result("pg18").label("\u538b\u529b\u5916\u573a");
    model.result("pg18").set("data", "grid1");
    model.result("pg18").set("titletype", "manual");
    model.result("pg18").set("title", "\u538b\u529b\u5916\u573a\uff08xy \u5e73\u9762\uff09");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").set("expr", "if(sqrt((x-x0)^2+y^2+z^2)>R,pext(x,y,z),NaN)");
    model.result("pg18").feature("surf1").set("rangecoloractive", true);
    model.result("pg18").feature("surf1").set("rangecolormin", "-.04");
    model.result("pg18").feature("surf1").set("rangecolormax", ".04");
    model.result("pg18").feature("surf1").set("colortable", "Wave");
    model.result("pg18").run();
    model.result("pg18").run();
    model.result("pg18").create("slc1", "Slice");
    model.result("pg18").feature("slc1").set("data", "dset9");
    model.result("pg18").feature("slc1").set("quickplane", "xy");
    model.result("pg18").feature("slc1").set("quickznumber", 1);
    model.result("pg18").feature("slc1").set("inheritplot", "surf1");
    model.result("pg18").run();
    model.result("pg13").run();
    model.result().create("pg19", "PlotGroup3D");
    model.result("pg19").run();
    model.result("pg19").label("\u7f29\u7565\u56fe");
    model.result("pg19").set("data", "dset10");
    model.result("pg19").set("showlegendsmaxmin", true);
    model.result("pg19").create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("colortable", "Wave");
    model.result("pg19").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg19").run();
    model.result("pg19").create("surf2", "Surface");
    model.result("pg19").feature("surf2").set("data", "dset4");
    model.result("pg19").feature("surf2").setIndex("looplevel", 87, 0);
    model.result("pg19").feature("surf2").set("expr", "mbd2.disp");
    model.result("pg19").feature("surf2").set("colortable", "AuroraAustralis");
    model.result("pg19").feature("surf2").create("def1", "Deform");
    model.result("pg19").run();
    model.result("pg19").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg19").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg19").run();
    model.result("pg19").create("iso1", "Isosurface");
    model.result("pg19").feature("iso1").set("number", 11);
    model.result("pg19").feature("iso1").set("inheritplot", "surf1");
    model.result("pg19").feature("iso1").create("filt1", "Filter");
    model.result("pg19").run();
    model.result("pg19").feature("iso1").feature("filt1").set("expr", "y>0");
    model.result("pg19").run();
    model.result("pg19").run();

    model.title("\u590d\u5408\u8f6e\u7cfb\u7684\u566a\u58f0\u8f90\u5c04");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u8f6e\u7cfb\u58f3\u4f53\u566a\u58f0\u8f90\u5c04\u7684\u5efa\u6a21\u3002\u6267\u884c\u77ac\u6001\u5206\u6790\uff0c\u8ba1\u7b97\u6307\u5b9a\u9a71\u52a8\u8f74\u901f\u5ea6\u7684\u58f3\u4f53\u632f\u52a8\u3002\u6240\u83b7\u5f97\u7684\u58f3\u4f53\u6cd5\u5411\u52a0\u901f\u5ea6\u8f6c\u6362\u4e3a\u9891\u57df\uff0c\u5e76\u7528\u4f5c\u566a\u58f0\u6e90\u3002\u7136\u540e\u5728\u9009\u5b9a\u9891\u7387\u4e0b\u6267\u884c\u58f0\u5b66\u5206\u6790\uff0c\u5e76\u5728\u8fd1\u573a\u3001\u8fdc\u573a\u548c\u5916\u573a\u4e2d\u8ba1\u7b97\u58f0\u538b\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("gear_train_noise.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
