/*
 * helicopter_swashplate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:20 by COMSOL 6.3.0.290. */
public class helicopter_swashplate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "helicopter_swashplate.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.func().create("rm1", "Ramp");
    model.func("rm1").set("location", "0.002[s]");
    model.func("rm1").set("cutoffactive", true);
    model.func("rm1").set("cutoff", "0.05[s]");
    model.func("rm1").set("smoothzonelocactive", true);
    model.func("rm1").set("smoothzoneloc", 0.005);
    model.func("rm1").set("smoothzonecutoffactive", true);
    model.func("rm1").set("smoothzonecutoff", 0.005);
    model.func().duplicate("rm2", "rm1");
    model.func("rm2").set("location", "0.004[s]");
    model.func("rm2").set("cutoff", "0.1[s]");
    model.func("rm2").set("smoothzoneloc", 0.01);
    model.func("rm2").set("smoothzonecutoff", 0.01);

    model.variable().create("var1");
    model.variable("var1").set("vf", "100[km/h]");
    model.variable("var1").descr("vf", "\u76f4\u5347\u673a\u7684\u524d\u8fdb\u901f\u5ea6");
    model.variable("var1").set("rpm", "300");
    model.variable("var1").descr("rpm", "\u76f4\u5347\u673a\u65cb\u7ffc\u8f6c\u901f");
    model.variable("var1").set("omega", "2*pi*rpm/60[s]");
    model.variable("var1").descr("omega", "\u65cb\u7ffc\u7684\u89d2\u901f\u5ea6");
    model.variable("var1").set("theta", "omega*t[rad]");
    model.variable("var1").descr("theta", "\u65cb\u7ffc\u7684\u89d2\u65cb\u8f6c");
    model.variable("var1").set("vp", "0.5[m/s]");
    model.variable("var1").descr("vp", "\u63a8\u6746\u8fd0\u52a8\u901f\u5ea6");
    model.variable("var1").set("up1", "vp*rm1(t)");
    model.variable("var1").descr("up1", "\u63a8\u6746 1 \u548c 3 \u7684\u8fd0\u52a8");
    model.variable("var1").set("up2", "vp*rm2(t)");
    model.variable("var1").descr("up2", "\u63a8\u6746 2 \u7684\u8fd0\u52a8");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").selection("sel1").label("\u6868\u53f6 1");
    model.component("comp1").selection("sel1").set(1, 2, 4, 5, 8, 9, 10, 12);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6868\u53f6 2");
    model.component("comp1").selection("sel2").set(17, 19, 20, 21, 22, 23, 24, 26);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6868\u53f6 3");
    model.component("comp1").selection("sel3").set(25, 29, 30, 31, 32, 33, 36, 37);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6868\u53f6");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u65e0\u6868\u53f6");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});

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

    model.component("comp1").physics("mbd").prop("InitialValues").set("omega_Init", new String[]{"0", "0", "omega"});
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u5e95\u677f");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(7);
    model.component("comp1").physics("mbd").feature("rd1").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u63a8\u6746 1");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(6);
    model.component("comp1").physics("mbd").feature("rd2").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature().duplicate("rd3", "rd2");
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u63a8\u6746 2");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(13);
    model.component("comp1").physics("mbd").feature().duplicate("rd4", "rd3");
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u63a8\u6746 3");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(35);
    model.component("comp1").physics("mbd").feature().duplicate("rd5", "rd4");
    model.component("comp1").physics("mbd").feature("rd5")
         .label("\u521a\u6027\u6750\u6599\uff1a\u5e95\u677f-\u6ed1\u76d8\u8fde\u6746 1");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(27);
    model.component("comp1").physics("mbd").feature().duplicate("rd6", "rd5");
    model.component("comp1").physics("mbd").feature("rd6")
         .label("\u521a\u6027\u6750\u6599\uff1a\u5e95\u677f-\u6ed1\u76d8\u8fde\u6746 2");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(28);
    model.component("comp1").physics("mbd").feature().duplicate("rd7", "rd6");
    model.component("comp1").physics("mbd").feature("rd7").label("\u521a\u6027\u6750\u6599\uff1a\u4e0b\u6ed1\u76d8");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(3);
    model.component("comp1").physics("mbd").create("rd8", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd8").label("\u521a\u6027\u6750\u6599\uff1a\u65cb\u7ffc\u6bc2");
    model.component("comp1").physics("mbd").feature("rd8").selection().set(14);
    model.component("comp1").physics("mbd").feature("rd8").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1")
         .set("CenterOfRotationType", "userDefined");
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1").set("Omega", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("rd8").feature("pdr1").set("phi0", "theta");
    model.component("comp1").physics("mbd").create("rd9", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd9").label("\u521a\u6027\u6750\u6599\uff1a\u4e0a\u6ed1\u76d8");
    model.component("comp1").physics("mbd").feature("rd9").selection().set(11);
    model.component("comp1").physics("mbd").feature().duplicate("rd10", "rd9");
    model.component("comp1").physics("mbd").feature("rd10").label("\u521a\u6027\u6750\u6599\uff1a\u7403\u4f53");
    model.component("comp1").physics("mbd").feature("rd10").selection().set(18);
    model.component("comp1").physics("mbd").feature().duplicate("rd11", "rd10");
    model.component("comp1").physics("mbd").feature("rd11")
         .label("\u521a\u6027\u6750\u6599\uff1a\u6ed1\u76d8-\u6868\u53f6\u8fde\u6746 1");
    model.component("comp1").physics("mbd").feature("rd11").selection().set(16);
    model.component("comp1").physics("mbd").feature().duplicate("rd12", "rd11");
    model.component("comp1").physics("mbd").feature("rd12")
         .label("\u521a\u6027\u6750\u6599\uff1a\u6ed1\u76d8-\u6868\u53f6\u8fde\u6746 2");
    model.component("comp1").physics("mbd").feature("rd12").selection().set(15);
    model.component("comp1").physics("mbd").feature().duplicate("rd13", "rd12");
    model.component("comp1").physics("mbd").feature("rd13")
         .label("\u521a\u6027\u6750\u6599\uff1a\u6ed1\u76d8-\u6868\u53f6\u8fde\u6746 3");
    model.component("comp1").physics("mbd").feature("rd13").selection().set(34);
    model.component("comp1").physics("mbd").feature().duplicate("rd14", "rd13");
    model.component("comp1").physics("mbd").feature("rd14")
         .label("\u521a\u6027\u6750\u6599\uff1a\u65cb\u7ffc\u6868\u53f6 1");
    model.component("comp1").physics("mbd").feature("rd14").selection().named("sel1");
    model.component("comp1").physics("mbd").feature().duplicate("rd15", "rd14");
    model.component("comp1").physics("mbd").feature("rd15")
         .label("\u521a\u6027\u6750\u6599\uff1a\u65cb\u7ffc\u6868\u53f6 2");
    model.component("comp1").physics("mbd").feature("rd15").selection().named("sel2");
    model.component("comp1").physics("mbd").feature().duplicate("rd16", "rd15");
    model.component("comp1").physics("mbd").feature("rd16")
         .label("\u521a\u6027\u6750\u6599\uff1a\u65cb\u7ffc\u6868\u53f6 3");
    model.component("comp1").physics("mbd").feature("rd16").selection().named("sel3");
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6ed1\u76d8-\u6868\u53f6 1");
    model.component("comp1").physics("mbd").feature("att1").selection().set(265, 266, 290, 291);
    model.component("comp1").physics("mbd").create("att2", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att2")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6ed1\u76d8-\u6868\u53f6 2");
    model.component("comp1").physics("mbd").feature("att2").selection().set(313, 314, 340, 341);
    model.component("comp1").physics("mbd").create("att3", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att3")
         .label("\u8fde\u63a5\u4ef6\uff1a\u6ed1\u76d8-\u6868\u53f6 3");
    model.component("comp1").physics("mbd").feature("att3").selection().set(687, 688, 695, 696);
    model.component("comp1").physics("mbd").create("att4", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att4").label("\u8fde\u63a5\u4ef6\uff1a\u6bc2-\u6868\u53f6 1");
    model.component("comp1").physics("mbd").feature("att4").selection().set(152, 153, 197, 198);
    model.component("comp1").physics("mbd").create("att5", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att5").label("\u8fde\u63a5\u4ef6\uff1a\u6bc2-\u6868\u53f6 2");
    model.component("comp1").physics("mbd").feature("att5").selection().set(414, 415, 445, 447);
    model.component("comp1").physics("mbd").create("att6", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att6").label("\u8fde\u63a5\u4ef6\uff1a\u6bc2-\u6868\u53f6 3");
    model.component("comp1").physics("mbd").feature("att6").selection().set(470, 471, 480, 482);
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("prj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("prj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("prj1").feature("cjp1").selection().set(48, 107);
    model.component("comp1").physics("mbd").feature("prj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("pm1").set("up", "up1");
    model.component("comp1").physics("mbd").create("rslj1", "ReducedSlotJoint", -1);
    model.component("comp1").physics("mbd").feature("rslj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("rslj1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("rslj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rslj1").set("JointTranslationalAxis", "AttachedOnSource");
    model.component("comp1").physics("mbd").feature("rslj1").set("e_tr", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("rslj1")
         .set("e_rot", new String[]{"cos(pi/6)", "sin(pi/6)", "0"});
    model.component("comp1").physics("mbd").feature("rslj1").feature("cjp1").selection().set(197, 248, 274, 378);
    model.component("comp1").physics("mbd").feature("rslj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("rslj1").feature("pm1").set("up", "up2");
    model.component("comp1").physics("mbd").feature("rslj1").feature("pm1")
         .set("ActivationConditionRotational", "neverActive");
    model.component("comp1").physics("mbd").create("prj2", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj2").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("prj2").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("prj2").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("prj2").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("prj2").feature("cjp1").selection().set(1115, 1219);
    model.component("comp1").physics("mbd").feature("prj2").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj2").feature("pm1").set("up", "up1");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(757, 774, 863, 896);
    model.component("comp1").physics("mbd").feature("hgj1").feature("ja1").selection().set(1236);
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("hgj2").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj2").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjp1").selection().set(914, 930, 980, 996);
    model.component("comp1").physics("mbd").feature("hgj2").feature("ja1").selection().set(1509);
    model.component("comp1").physics("mbd").create("blj1", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj1").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("blj1").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("blj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj1").feature("cjp1").selection().set(73, 75);
    model.component("comp1").physics("mbd").create("blj2", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj2").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("blj2").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("blj2").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj2").feature("cjp1").selection().set(263, 265);
    model.component("comp1").physics("mbd").create("blj3", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj3").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature("blj3").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("blj3").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj3").feature("cjp1").selection().set(1062, 1240);
    model.component("comp1").physics("mbd").create("blj4", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj4").set("Source", "rd6");
    model.component("comp1").physics("mbd").feature("blj4").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("blj4").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj4").feature("cjp1").selection().set(798, 834);
    model.component("comp1").physics("mbd").create("blj5", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj5").set("Source", "rd10");
    model.component("comp1").physics("mbd").feature("blj5").set("Destination", "rd7");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").feature("blj5").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj5").feature("cjp1").selection().set(558, 723);
    model.component("comp1").physics("mbd").create("blj6", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj6").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("blj6").set("Destination", "rd11");
    model.component("comp1").physics("mbd").feature("blj6").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj6").feature("cjp1").selection().set(337, 490);
    model.component("comp1").physics("mbd").create("blj7", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj7").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("blj7").set("Destination", "rd12");
    model.component("comp1").physics("mbd").feature("blj7").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj7").feature("cjp1").selection().set(342, 491);
    model.component("comp1").physics("mbd").create("blj8", "BallJoint", -1);
    model.component("comp1").physics("mbd").feature("blj8").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("blj8").set("Destination", "rd13");
    model.component("comp1").physics("mbd").feature("blj8").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("blj8").feature("cjp1").selection().set(993, 1208);
    model.component("comp1").physics("mbd").create("plj1", "PlanarJoint", -1);
    model.component("comp1").physics("mbd").feature("plj1").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("plj1").set("Destination", "rd9");
    model.component("comp1").physics("mbd").feature("plj1").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("plj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").create("clj1", "CylindricalJoint", -1);
    model.component("comp1").physics("mbd").feature("clj1").set("Source", "rd10");
    model.component("comp1").physics("mbd").feature("clj1").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature("clj1").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("clj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("clj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("clj1").feature("pm1")
         .set("ActivationConditionTranslational", "neverActive");
    model.component("comp1").physics("mbd").create("hgj3", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd11");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj3").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj3").feature("ja1").selection().set(551);
    model.component("comp1").physics("mbd").create("hgj4", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj4").set("Source", "rd12");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("hgj4").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj4").feature("ja1").selection().set(660);
    model.component("comp1").physics("mbd").create("hgj5", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj5").set("Source", "rd13");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "att3");
    model.component("comp1").physics("mbd").feature("hgj5").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj5").feature("ja1").selection().set(1681);
    model.component("comp1").physics("mbd").create("hgj6", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj6").set("Source", "rd8");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "att4");
    model.component("comp1").physics("mbd").feature("hgj6").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj6").feature("ja1").selection().set(535);
    model.component("comp1").physics("mbd").create("hgj7", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj7").set("Source", "rd8");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "att5");
    model.component("comp1").physics("mbd").feature("hgj7").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj7").feature("ja1").selection().set(1053);
    model.component("comp1").physics("mbd").create("hgj8", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj8").set("Source", "rd8");
    model.component("comp1").physics("mbd").feature("hgj8").set("Destination", "att6");
    model.component("comp1").physics("mbd").feature("hgj8").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj8").feature("ja1").selection().set(1144);

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 3);
    model.component("comp1").variable("var2").selection().named("sel1");
    model.component("comp1").variable("var2").set("alpha", "-mbd.hgj6.th");
    model.component("comp1").variable("var2").descr("alpha", "\u653b\u89d2");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 3);
    model.component("comp1").variable("var3").selection().named("sel2");
    model.component("comp1").variable("var3").set("alpha", "mbd.hgj7.th", "\u653b\u89d2");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").selection().geom("geom1", 3);
    model.component("comp1").variable("var4").selection().named("sel3");
    model.component("comp1").variable("var4").set("alpha", "mbd.hgj8.th", "\u653b\u89d2");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set(400);
    model.component("comp1").cpl().duplicate("intop3", "intop2");
    model.component("comp1").cpl("intop3").selection().set(714);
    model.component("comp1").cpl().duplicate("intop4", "intop3");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop4").selection().set(580);

    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").set("d", "sqrt(x^2+y^2)");
    model.component("comp1").variable("var5").descr("d", "\u5230\u4e2d\u5fc3\u7684\u8ddd\u79bb");
    model.component("comp1").variable("var5").set("Ur", "omega*d-vf*y/d");
    model.component("comp1").variable("var5").descr("Ur", "\u76f8\u5bf9\u901f\u5ea6");
    model.component("comp1").variable("var5").set("CL", "4*alpha");
    model.component("comp1").variable("var5").descr("CL", "\u5347\u529b\u7cfb\u6570");
    model.component("comp1").variable("var5").set("FL", "0.5*1[kg/m^3]*Ur^2*CL*sign(Ur)");
    model.component("comp1").variable("var5").descr("FL", "\u5347\u529b");
    model.component("comp1").variable("var5").set("FL_tot1", "intop1(FL)");
    model.component("comp1").variable("var5").descr("FL_tot1", "\u603b\u5347\u529b - \u6868\u53f6 1");
    model.component("comp1").variable("var5").set("FL_tot2", "intop2(FL)");
    model.component("comp1").variable("var5").descr("FL_tot2", "\u603b\u5347\u529b - \u6868\u53f6 2");
    model.component("comp1").variable("var5").set("FL_tot3", "intop3(FL)");
    model.component("comp1").variable("var5").descr("FL_tot3", "\u603b\u5347\u529b - \u6868\u53f6 3");
    model.component("comp1").variable("var5").set("alphaM", "9.4[deg]");
    model.component("comp1").variable("var5").descr("alphaM", "\u5e73\u5747\u653b\u89d2");
    model.component("comp1").variable("var5").set("CL_alphaM", "4*alphaM");
    model.component("comp1").variable("var5").descr("CL_alphaM", "\u5e73\u5747\u5347\u529b\u7cfb\u6570");
    model.component("comp1").variable("var5").set("FL_tot1_alphaM", "intop1(0.5*1[kg/m^3]*Ur^2*CL_alphaM*sign(Ur))");
    model.component("comp1").variable("var5")
         .descr("FL_tot1_alphaM", "\u603b\u5347\u529b\uff08\u5e73\u5747\uff09- \u6868\u53f6 1");
    model.component("comp1").variable("var5").set("w_tip", "intop4(w)");
    model.component("comp1").variable("var5").descr("w_tip", "z \u65b9\u5411\u7684\u53f6\u5c16\u4f4d\u79fb");

    model.component("comp1").physics("mbd").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("mbd").feature("bndl1").selection().set(3, 400, 714);
    model.component("comp1").physics("mbd").feature("bndl1").set("forceReferenceArea", new String[]{"0", "0", "FL"});

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").add("rd5");
    model.nodeGroup("grp1").add("rd6");
    model.nodeGroup("grp1").add("rd7");
    model.nodeGroup("grp1").add("rd8");
    model.nodeGroup("grp1").add("rd9");
    model.nodeGroup("grp1").add("rd10");
    model.nodeGroup("grp1").add("rd11");
    model.nodeGroup("grp1").add("rd12");
    model.nodeGroup("grp1").add("rd13");
    model.nodeGroup("grp1").add("rd14");
    model.nodeGroup("grp1").add("rd15");
    model.nodeGroup("grp1").add("rd16");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("att1");
    model.nodeGroup("grp2").add("att2");
    model.nodeGroup("grp2").add("att3");
    model.nodeGroup("grp2").add("att4");
    model.nodeGroup("grp2").add("att5");
    model.nodeGroup("grp2").add("att6");
    model.nodeGroup("grp2").label("\u8fde\u63a5\u4ef6");
    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("prj1");
    model.nodeGroup("grp3").add("prj2");
    model.nodeGroup("grp3").label("\u68f1\u67f1\u5173\u8282");
    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("rslj1");
    model.nodeGroup("grp4").add("hgj1");
    model.nodeGroup("grp4").add("hgj2");
    model.nodeGroup("grp4").add("hgj3");
    model.nodeGroup("grp4").add("hgj4");
    model.nodeGroup("grp4").add("hgj5");
    model.nodeGroup("grp4").add("hgj6");
    model.nodeGroup("grp4").add("hgj7");
    model.nodeGroup("grp4").add("hgj8");
    model.nodeGroup("grp4").label("\u94f0\u94fe\u5173\u8282");
    model.nodeGroup().create("grp5", "Physics", "mbd");
    model.nodeGroup("grp5").placeAfter("rslj1");
    model.nodeGroup("grp5").add("blj1");
    model.nodeGroup("grp5").add("blj2");
    model.nodeGroup("grp5").add("blj3");
    model.nodeGroup("grp5").add("blj4");
    model.nodeGroup("grp5").add("blj5");
    model.nodeGroup("grp5").add("blj6");
    model.nodeGroup("grp5").add("blj7");
    model.nodeGroup("grp5").add("blj8");
    model.nodeGroup("grp5").label("\u7403\u5173\u8282");

    model.study("std1").feature("time").set("tlist", "range(0,0.001,0.3)");
    model.study("std1").label("\u7814\u7a76\uff1a\u521a\u6027\u6868\u53f6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();

    model.component("comp1").physics("mbd").prop("Results").set("ReferenceFrame", "att4");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.001,0.3)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"mbd/rd14", "mbd/rd15", "mbd/rd16"});
    model.study("std2").label("\u7814\u7a76\uff1a\u67d4\u6027\u6868\u53f6");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol2").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol2").feature("t1").feature("fc1").set("aaccmix", 0.9);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().named("sel1");
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().set();
    model.result().dataset("dset4").selection().named("com1");
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\uff1a\u67d4\u6027\u6868\u53f6 1");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("frametype", "material");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.disp_ref");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg3").feature("surf1").feature("def1").set("scale", 50);
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff1a\u67d4\u6027\u6868\u53f6 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("view", "view1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "dset3");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "mbd.mises");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", 0);
    model.result("pg4").feature("surf1").set("rangecolormax", "3e7");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"u", "v", "w+10*w_ref"});
    model.result("pg4").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset4");
    model.result("pg4").feature("surf2").set("expr", "dom");
    model.result("pg4").feature("surf2").set("titletype", "none");
    model.result("pg4").feature("surf2").set("coloring", "uniform");
    model.result("pg4").feature("surf2").set("color", "gray");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u653b\u89d2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "-mbd.hgj6.th", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u76f8\u5bf9\u65cb\u8f6c", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.hgj7.th", 1);
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.hgj8.th", 2);
    model.result("pg5").feature("glob1").setIndex("unit", "deg", 2);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "theta*180/pi");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u653b\u89d2\uff1a\u6868\u53f6 1", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "\u653b\u89d2\uff1a\u6868\u53f6 2", 1);
    model.result("pg5").feature("glob1").setIndex("legends", "\u653b\u89d2\uff1a\u6868\u53f6 3", 2);
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u65cb\u7ffc\u7684\u89d2\u65cb\u8f6c (deg)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u653b\u89d2 (deg)");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymax", 25);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u603b\u5347\u529b");
    model.result("pg6").setIndex("looplevelinput", "interp", 0);
    model.result("pg6").setIndex("interp", "range(0,0.003,0.3)", 0);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"FL_tot1"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u603b\u5347\u529b - \u6868\u53f6 1"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"FL_tot1", "FL_tot2"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u603b\u5347\u529b - \u6868\u53f6 1", "\u603b\u5347\u529b - \u6868\u53f6 2"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"FL_tot1", "FL_tot2", "FL_tot3"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u603b\u5347\u529b - \u6868\u53f6 1", "\u603b\u5347\u529b - \u6868\u53f6 2", "\u603b\u5347\u529b - \u6868\u53f6 3"});
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "theta*180/pi");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("linemarker", "cycle");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65cb\u7ffc\u7684\u89d2\u65cb\u8f6c (deg)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u603b\u5347\u529b (N)");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u603b\u5347\u529b\uff1a\u6868\u53f6 1");
    model.result("pg7").setIndex("interp", "range(0.1,0.003,0.3)", 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"FL_tot1"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u603b\u5347\u529b - \u6868\u53f6 1"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"FL_tot1", "FL_tot1_alphaM"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u603b\u5347\u529b - \u6868\u53f6 1", "\u603b\u5347\u529b\uff08\u5e73\u5747\uff09- \u6868\u53f6 1"});
    model.result("pg7").run();
    model.result("pg7").set("ylabel", "\u6868\u53f6 1 \u4e0a\u7684\u603b\u5347\u529b (N)");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5c16\u7aef\u4f4d\u79fb");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("data", "dset1");
    model.result("pg8").feature("glob1").set("expr", new String[]{"w_tip"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"z \u65b9\u5411\u7684\u53f6\u5c16\u4f4d\u79fb"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg8").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "theta*180/pi");
    model.result("pg8").feature("glob1").set("linewidth", 2);
    model.result("pg8").feature("glob1").set("linemarker", "cycle");
    model.result("pg8").feature("glob1").set("markerpos", "interp");
    model.result("pg8").feature("glob1").set("legendmethod", "manual");
    model.result("pg8").feature("glob1")
         .setIndex("legends", "z \u65b9\u5411\u7684\u53f6\u5c16\u4f4d\u79fb\uff08\u521a\u6027\u6868\u53f6\uff09", 0);
    model.result("pg8").feature().duplicate("glob2", "glob1");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("data", "dset3");
    model.result("pg8").feature("glob2")
         .setIndex("legends", "z \u65b9\u5411\u7684\u53f6\u5c16\u4f4d\u79fb\uff08\u67d4\u6027\u6868\u53f6\uff09", 0);
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u65cb\u7ffc\u7684\u89d2\u65cb\u8f6c (deg)");
    model.result("pg8").run();

    model.study().create("std3");
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("solnum", "auto");
    model.study("std3").feature("eig").set("notsolnum", "auto");
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/mbd", true);
    model.study("std3").feature("eig").set("neigsactive", true);
    model.study("std3").feature("eig").set("neigs", 20);
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig")
         .set("disabledphysics", new String[]{"mbd/rd14", "mbd/rd15", "mbd/rd16", "mbd/prj1/pm1", "mbd/prj2/pm1", "mbd/rslj1/pm1"});
    model.study("std3").label("\u7814\u7a76\uff1a\u67d4\u6027\u6868\u53f6[\u7279\u5f81\u9891\u7387]");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u632f\u578b (mbd)");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").set("showlegends", "off");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature("surf1").feature().create("def1", "Deform");
    model.result("pg9").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().evaluationGroup().create("std3EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std3EvgFrq").set("data", "dset5");
    model.result().evaluationGroup("std3EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76\uff1a\u67d4\u6027\u6868\u53f6[\u7279\u5f81\u9891\u7387])");
    model.result().evaluationGroup("std3EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std3EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std3EvgFrq").run();
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{6});
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg9").feature("surf1").feature("def1").set("scale", 0.02);
    model.result("pg9").run();
    model.result("pg9").set("view", "view1");
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{16});
    model.result("pg9").run();
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
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").set("plotgroup", "pg4");
    model.result("pg4").run();
    model.result("pg4").set("view", "view2");
    model.result("pg4").run();

    return model;
  }

  public static Model run3(Model model) {

    model.title("\u76f4\u5347\u673a\u6ed1\u76d8\u673a\u6784");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5bf9\u6ed1\u76d8\u673a\u6784\u5efa\u6a21\uff0c\u6b64\u673a\u6784\u5e38\u7528\u4e8e\u63a7\u5236\u76f4\u5347\u673a\u65cb\u7ffc\u6868\u53f6\u7684\u65b9\u5411\u3002\u65cb\u7ffc\u6868\u53f6\u5728\u4e24\u79cd\u60c5\u51b5\u4e0b\u5206\u522b\u5efa\u6a21\u4e3a\u521a\u6027\u548c\u67d4\u6027\u3002\u8ba1\u7b97\u4e86\u7ed5\u4e24\u4e2a\u4e0d\u540c\u8f74\u65cb\u8f6c\u65f6\u65cb\u7ffc\u6868\u53f6\u7684\u53d8\u5f62\u3002\u6b64\u673a\u6784\u7684\u5176\u4ed6\u90e8\u5206\u6a21\u62df\u4e3a\u521a\u6027\u3002\u8fd8\u5bf9\u6b64\u673a\u6784\u6267\u884c\u4e86\u7279\u5f81\u9891\u7387\u5206\u6790\u3002");

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("helicopter_swashplate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
