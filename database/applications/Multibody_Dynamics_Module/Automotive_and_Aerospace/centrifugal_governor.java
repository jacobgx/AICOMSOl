/*
 * centrifugal_governor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:19 by COMSOL 6.3.0.290. */
public class centrifugal_governor {

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

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "centrifugal_governor.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("k", "3000[N/m]");
    model.component("comp1").variable("var1").descr("k", "\u5f39\u7c27\u5e38\u6570");
    model.component("comp1").variable("var1").set("c", "10[N*s/m]");
    model.component("comp1").variable("var1").descr("c", "\u963b\u5c3c\u7cfb\u6570");
    model.component("comp1").variable("var1").set("rpm", "1000*step1(t)");
    model.component("comp1").variable("var1").descr("rpm", "\u4e3b\u8f74\u6bcf\u5206\u949f\u8f6c\u6570");
    model.component("comp1").variable("var1").set("omega", "(2*pi*rpm/60)[rad/s]");
    model.component("comp1").variable("var1").descr("omega", "\u4e3b\u8f74\u7684\u89d2\u901f\u5ea6");
    model.component("comp1").variable("var1").set("N", "rpm/60[s]*t");
    model.component("comp1").variable("var1").descr("N", "\u4e3b\u8f74\u8f6c\u901f");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "0.125[s]");
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 2);
    model.component("comp1").func("step1").set("smooth", 0.01);

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
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1").selection().set(119, 120, 127, 129);
    model.component("comp1").physics("mbd").create("att2", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att2").selection().set(89, 90, 92, 93);
    model.component("comp1").physics("mbd").create("att3", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att3").selection().set(101, 102, 104, 105);
    model.component("comp1").physics("mbd").create("att4", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att4").selection().set(191, 192, 193, 195);
    model.component("comp1").physics("mbd").create("att5", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att5").selection().set(210, 211, 212, 214);
    model.component("comp1").physics("mbd").create("att6", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att6").selection().set(166, 167, 168, 170, 177, 178);
    model.component("comp1").physics("mbd").create("att7", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att7").selection().set(138, 139, 141, 142);
    model.component("comp1").physics("mbd").create("att8", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att8").selection().set(149, 150, 151, 153);
    model.component("comp1").physics("mbd").create("att9", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att9").selection().set(36, 37, 39, 41);
    model.component("comp1").physics("mbd").create("att10", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att10").selection().set(111, 112, 114, 115);
    model.component("comp1").physics("mbd").create("att11", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att11").selection().set(16, 17, 18, 19, 22, 24);
    model.component("comp1").physics("mbd").create("att12", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att12").selection().set(48, 49, 50, 52);
    model.component("comp1").physics("mbd").create("att13", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att13").selection().set(66, 67, 69, 71);
    model.component("comp1").physics("mbd").create("att14", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att14").selection().set(77, 78, 80, 81);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("att1");
    model.nodeGroup("grp1").add("att2");
    model.nodeGroup("grp1").add("att3");
    model.nodeGroup("grp1").add("att4");
    model.nodeGroup("grp1").add("att5");
    model.nodeGroup("grp1").add("att6");
    model.nodeGroup("grp1").add("att7");
    model.nodeGroup("grp1").add("att8");
    model.nodeGroup("grp1").add("att9");
    model.nodeGroup("grp1").add("att10");
    model.nodeGroup("grp1").add("att11");
    model.nodeGroup("grp1").add("att12");
    model.nodeGroup("grp1").add("att13");
    model.nodeGroup("grp1").add("att14");
    model.nodeGroup("grp1").label("\u8fde\u63a5\u4ef6");

    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "att1");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("prj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("prj1").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("k_u", "k");
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("c_u", "c");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "att3");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att4");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "att5");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att6");
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "att7");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "att8");
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Source", "att9");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "att10");
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj4");
    model.component("comp1").physics("mbd").feature("hgj5").set("Source", "att11");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "att12");
    model.component("comp1").physics("mbd").feature().duplicate("hgj6", "hgj5");
    model.component("comp1").physics("mbd").feature("hgj6").set("Source", "att13");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "att14");
    model.component("comp1").physics("mbd").feature().duplicate("hgj7", "hgj6");
    model.component("comp1").physics("mbd").feature("hgj7").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj7").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("hgj7").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj7").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj7").feature("pm1").set("omegap", "omega");

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("prj1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").add("hgj5");
    model.nodeGroup("grp2").add("hgj6");
    model.nodeGroup("grp2").add("hgj7");
    model.nodeGroup("grp2").label("\u5173\u8282");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);

    model.study("std1").feature("time").set("tlist", "range(0,0.001,0.25)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("tstepsbdf", "free");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 0.005);
    model.sol("sol1").feature("t1").set("consistent", false);

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
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 161, 0);
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").selection().set(1);
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature("pttraj1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.misesGp");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u76f8\u5bf9\u4f4d\u79fb");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.prj1.u"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "N");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u56fe");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.prj1.u_t"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u901f\u5ea6"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m/s"});
    model.result("pg5").feature("glob1").set("titletype", "manual");
    model.result("pg5").feature("glob1").set("title", "\u76f8\u56fe");
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "mbd.prj1.u");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f39\u7c27\u529b\u548c\u963b\u5c3c\u529b");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u529b (N)");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.prj1.sd1.Fs", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.prj1.sd1.Fd", 1);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "N");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("linemarker", "cycle");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5173\u8282\u529b");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u529b (N)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.hgj3.Fx"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.hgj3.Fx", "mbd.hgj3.Fy"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\uff0cy \u5206\u91cf"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.hgj3.Fx", "mbd.hgj3.Fy", "mbd.hgj3.Fz"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\uff0cy \u5206\u91cf", "\u5173\u8282\u529b\uff0cz \u5206\u91cf"});
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "N");
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("markerpos", "interp");
    model.result("pg7").run();
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
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u5f39\u7c27\u79bb\u5fc3\u8c03\u901f\u5668");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u6a21\u62df\u5f39\u7c27\u79bb\u5fc3\u8c03\u901f\u5668\u3002\u673a\u6784\u4e2d\u7684\u90e8\u4ef6\u4f5c\u4e3a\u67d4\u6027\u5355\u5143\u8fdb\u884c\u5efa\u6a21\uff0c\u4e0d\u540c\u90e8\u4ef6\u4e4b\u95f4\u7684\u8fde\u63a5\u901a\u8fc7\u94f0\u94fe\u5173\u8282\u548c\u68f1\u67f1\u5173\u8282\u8fdb\u884c\u5efa\u6a21\u3002\u68f1\u67f1\u5173\u8282\u4e0a\u7684\u5f39\u7c27\u548c\u7f13\u51b2\u5668\u7528\u4e8e\u63a7\u5236\u673a\u6784\u7684\u8fd0\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("centrifugal_governor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
