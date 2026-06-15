/*
 * truck_mounted_crane.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:23 by COMSOL 6.3.0.290. */
public class truck_mounted_crane {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Machinery_and_Robotics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "truck_mounted_crane.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u5e95\u5ea7");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(1, 10, 17, 22, 23);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").label("\u60ac\u81c2 1");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(19, 20, 21);
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd3").label("\u60ac\u81c2 2");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(2, 4, 13);
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd4").label("\u4f38\u7f29\u81c2 1");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(5, 11, 14);
    model.component("comp1").physics("mbd").create("rd5", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd5").label("\u4f38\u7f29\u81c2 2");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(6, 8, 12);
    model.component("comp1").physics("mbd").create("rd6", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd6").label("\u4f38\u7f29\u81c2 3");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(3, 7, 9);
    model.component("comp1").physics("mbd").create("rd7", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd7").label("\u6cb9\u7f38 1");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(29);
    model.component("comp1").physics("mbd").create("rd8", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd8").label("\u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("rd8").selection().set(27);
    model.component("comp1").physics("mbd").create("rd9", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd9").label("\u6cb9\u7f38 2");
    model.component("comp1").physics("mbd").feature("rd9").selection().set(30);
    model.component("comp1").physics("mbd").create("rd10", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd10").label("\u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("rd10").selection().set(28);
    model.component("comp1").physics("mbd").create("rd11", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd11").label("\u8fde\u6746 1");
    model.component("comp1").physics("mbd").feature("rd11").selection().set(15);
    model.component("comp1").physics("mbd").create("rd12", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd12").label("\u8fde\u6746 2");
    model.component("comp1").physics("mbd").feature("rd12").selection().set(18, 25, 32);
    model.component("comp1").physics("mbd").create("rd13", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd13").label("\u8fde\u6746 3");
    model.component("comp1").physics("mbd").feature("rd13").selection().set(16);
    model.component("comp1").physics("mbd").create("rd14", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd14").label("\u8fde\u6746 4");
    model.component("comp1").physics("mbd").feature("rd14").selection().set(24, 26, 31);
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u5e95\u5ea7-\u60ac\u81c2 1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj1")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjb1").selection().set(404, 413);
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u5e95\u5ea7-\u6cb9\u7f38 1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd7");
    model.component("comp1").physics("mbd").feature("hgj2")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjb1").selection().set(589, 598);
    model.component("comp1").physics("mbd").create("hgj3", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj3")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u5e95\u5ea7-\u8fde\u6746 1");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd11");
    model.component("comp1").physics("mbd").feature("hgj3")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("hgj3").feature("cjb1").selection().set(365, 366);
    model.component("comp1").physics("mbd").create("hgj4", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj4")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u60ac\u81c2 1-\u8fde\u6746 2");
    model.component("comp1").physics("mbd").feature("hgj4").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "rd12");
    model.component("comp1").physics("mbd").feature("hgj4")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("hgj4").feature("cjb1").selection().set(414, 423);
    model.component("comp1").physics("mbd").create("slj1", "SlotJoint", -1);
    model.component("comp1").physics("mbd").feature("slj1")
         .label("\u69fd\u5173\u8282\uff0c\u8fde\u6746 1-\u8fde\u6746 2");
    model.component("comp1").physics("mbd").feature("slj1").set("Source", "rd11");
    model.component("comp1").physics("mbd").feature("slj1").set("Destination", "rd12");
    model.component("comp1").physics("mbd").feature("slj1")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("slj1").feature("cjb1").selection().set(362, 363);
    model.component("comp1").physics("mbd").feature().duplicate("slj2", "slj1");
    model.component("comp1").physics("mbd").feature("slj2")
         .label("\u69fd\u5173\u8282\uff0c\u8fde\u6746 1-\u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("slj2").set("Destination", "rd8");
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1")
         .label("\u68f1\u67f1\u5173\u8282\uff0c\u6cb9\u7f38 1-\u6d3b\u585e 1");
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd8");
    model.component("comp1").physics("mbd").feature("prj1").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("prj1").set("ReverseDirection", true);
    model.component("comp1").physics("mbd").feature("prj1")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("prj1").feature("cjb1").selection().set(710);
    model.component("comp1").physics("mbd").feature("prj1").feature("ja1").selection().set(1564);
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj1");
    model.component("comp1").physics("mbd").feature().duplicate("hgj6", "hgj2");
    model.component("comp1").physics("mbd").feature().duplicate("hgj7", "hgj3");
    model.component("comp1").physics("mbd").feature().duplicate("hgj8", "hgj4");
    model.component("comp1").physics("mbd").feature().duplicate("slj3", "slj1");
    model.component("comp1").physics("mbd").feature().duplicate("slj4", "slj2");
    model.component("comp1").physics("mbd").feature().duplicate("prj2", "prj1");
    model.component("comp1").physics("mbd").feature("hgj5")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u60ac\u81c2 1-\u60ac\u81c2 2");
    model.component("comp1").physics("mbd").feature("hgj5").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj5").feature("cjb1").selection().set(438, 439);
    model.component("comp1").physics("mbd").feature("hgj6")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u60ac\u81c2 1-\u6cb9\u7f38 2");
    model.component("comp1").physics("mbd").feature("hgj6").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj6").set("Destination", "rd9");
    model.component("comp1").physics("mbd").feature("hgj6").feature("cjb1").selection().set(424, 433);
    model.component("comp1").physics("mbd").feature("hgj7")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u60ac\u81c2 1-\u8fde\u6746 3");
    model.component("comp1").physics("mbd").feature("hgj7").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj7").set("Destination", "rd13");
    model.component("comp1").physics("mbd").feature("hgj7").feature("cjb1").selection().set(391, 392);
    model.component("comp1").physics("mbd").feature("hgj8")
         .label("\u94f0\u94fe\u5173\u8282\uff0c\u60ac\u81c2 2-\u8fde\u6746 4");
    model.component("comp1").physics("mbd").feature("hgj8").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj8").set("Destination", "rd14");
    model.component("comp1").physics("mbd").feature("hgj8").feature("cjb1").selection().set(603, 604);
    model.component("comp1").physics("mbd").feature("slj3")
         .label("\u69fd\u5173\u8282\uff0c\u8fde\u6746 3-\u8fde\u6746 4");
    model.component("comp1").physics("mbd").feature("slj3").set("Source", "rd13");
    model.component("comp1").physics("mbd").feature("slj3").set("Destination", "rd14");
    model.component("comp1").physics("mbd").feature("slj3").feature("cjb1").selection().set(388, 389);
    model.component("comp1").physics("mbd").feature("slj4")
         .label("\u69fd\u5173\u8282\uff0c\u8fde\u6746 3-\u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("slj4").set("Source", "rd13");
    model.component("comp1").physics("mbd").feature("slj4").set("Destination", "rd10");
    model.component("comp1").physics("mbd").feature("slj4").feature("cjb1").selection().set(388, 389);
    model.component("comp1").physics("mbd").feature("prj2")
         .label("\u68f1\u67f1\u5173\u8282\uff0c\u6cb9\u7f38 2-\u6d3b\u585e 2");
    model.component("comp1").physics("mbd").feature("prj2").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature("prj2").set("Destination", "rd10");
    model.component("comp1").physics("mbd").feature("prj2").set("ReverseDirection", false);
    model.component("comp1").physics("mbd").feature("prj2").feature("cjb1").selection().set(746);
    model.component("comp1").physics("mbd").feature("prj2").feature("ja1").selection().set(1682);
    model.component("comp1").physics("mbd").create("prj3", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj3")
         .label("\u68f1\u67f1\u5173\u8282\uff0c\u60ac\u81c2 2-\u4f38\u7f29\u81c2 1");
    model.component("comp1").physics("mbd").feature("prj3").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("prj3").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("prj3").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("prj3")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("prj3").feature("cjb1").selection().set(315, 316);
    model.component("comp1").physics("mbd").create("prj4", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj4")
         .label("\u68f1\u67f1\u5173\u8282\uff0c\u4f38\u7f29\u81c2 1-\u4f38\u7f29\u81c2 2");
    model.component("comp1").physics("mbd").feature("prj4").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature("prj4").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("prj4").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("prj4")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("prj4").feature("cjb1").selection().set(280, 284);
    model.component("comp1").physics("mbd").create("prj5", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj5")
         .label("\u68f1\u67f1\u5173\u8282\uff0c\u4f38\u7f29\u81c2 2-\u4f38\u7f29\u81c2 3");
    model.component("comp1").physics("mbd").feature("prj5").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature("prj5").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("prj5").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("prj5")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("prj5").feature("cjb1").selection().set(223, 224);
    model.component("comp1").physics("mbd").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("mbd").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("mbd").feature("bl1").selection().set(3);
    model.component("comp1").physics("mbd").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("mbd").feature("bl1").set("force", new String[]{"0", "0", "-1000[kg]*g_const"});

    model.param().set("Angle1", "0[deg]");
    model.param().descr("Angle1", "\u6c34\u5e73\u89d2\u5ea6\uff0c\u5927\u81c2");
    model.param().set("RelAng", "0[deg]");
    model.param().descr("RelAng", "\u60ac\u81c2\u4e4b\u95f4\u7684\u5939\u89d2");
    model.param().set("ExtLen", "0[m]");
    model.param().descr("ExtLen", "\u603b\u4f38\u7f29\u957f\u5ea6");

    model.component("comp1").physics("mbd").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("name", "cyl1Pos", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("equation", "Angle1-mbd.hgj1.th*180/pi", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1")
         .setIndex("description", "\u5185\u6cb9\u7f38\u4f38\u957f\u957f\u5ea6", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("mbd").feature("ge1").setIndex("name", "cyl2Pos", 1, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("equation", "RelAng-mbd.hgj5.th*180/pi", 1, 0);
    model.component("comp1").physics("mbd").feature("ge1")
         .setIndex("description", "\u5916\u6cb9\u7f38\u4f38\u957f\u957f\u5ea6", 1, 0);
    model.component("comp1").physics("mbd").feature("prj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("pm1").set("up", "cyl1Pos");
    model.component("comp1").physics("mbd").feature("prj1").feature("pm1").set("UnidirectionalConstraints", true);
    model.component("comp1").physics("mbd").feature("prj2").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj2").feature("pm1").set("up", "cyl2Pos");
    model.component("comp1").physics("mbd").feature("prj2").feature("pm1").set("UnidirectionalConstraints", true);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").feature("prj3").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj3").feature("pm1").set("up", "ExtLen/3");
    model.component("comp1").physics("mbd").feature("prj4").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj4").feature("pm1").set("up", "ExtLen/3");
    model.component("comp1").physics("mbd").feature("prj5").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj5").feature("pm1").set("up", "ExtLen/3");

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
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").add("hgj4");
    model.nodeGroup("grp2").add("hgj5");
    model.nodeGroup("grp2").add("hgj6");
    model.nodeGroup("grp2").add("hgj7");
    model.nodeGroup("grp2").add("hgj8");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");
    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("slj1");
    model.nodeGroup("grp3").add("slj2");
    model.nodeGroup("grp3").add("slj3");
    model.nodeGroup("grp3").add("slj4");
    model.nodeGroup("grp3").label("\u69fd\u5173\u8282");
    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("init1");
    model.nodeGroup("grp4").add("prj1");
    model.nodeGroup("grp4").add("prj2");
    model.nodeGroup("grp4").add("prj3");
    model.nodeGroup("grp4").add("prj4");
    model.nodeGroup("grp4").add("prj5");
    model.nodeGroup("grp4").label("\u68f1\u67f1\u5173\u8282");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(2, 3, 4, 10, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 31, 32);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection()
         .set(5, 6, 7, 17, 18, 19, 20, 23, 24);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat")
         .set("plistarr", new String[]{"-15   0  15  30  45  45  45  45  45  45  45   60   60", "0   0   0   0   0   0   0   0 -30 -60 -90 -120 -135", "5.5 5.1 5.5 5.5 5.5 4.5 3.5 2.5 1.5 1.5 1.5  1.5  1.5"});
    model.study("std1").feature("stat").set("pname", new String[]{"Angle1", "RelAng", "ExtLen"});
    model.study("std1").feature("stat").set("punit", new String[]{"rad", "rad", "rad"});
    model.study("std1").feature("stat").set("pcontinuationmode", "no");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ODE1").set("scaleval", 0.1);

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").createAutoSequences("all");

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

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

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
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u60ac\u81c2\u6cb9\u7f38\u529b");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "mbd.prj1.Fl1", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u6cb9\u7f38 1", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "mbd.prj2.Fl1", 1);
    model.result("pg2").feature("glob1").setIndex("unit", "kN", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u6cb9\u7f38 2", 1);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u529b [kN]");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u4f38\u7f29\u6cb9\u7f38\u529b");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.prj3.Fl1", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4f38\u7f29\u6cb9\u7f38 1", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.prj4.Fl1", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4f38\u7f29\u6cb9\u7f38 2", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mbd.prj5.Fl1", 2);
    model.result("pg3").feature("glob1").setIndex("unit", "kN", 2);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4f38\u7f29\u6cb9\u7f38 3", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u94f0\u94fe\u529b");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.hgj1.Fy", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5185\u90e8\u94f0\u94fe\u529b\uff0cy", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.hgj1.Fz", 1);
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5185\u90e8\u94f0\u94fe\u529b\uff0cz", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.hgj5.Fy", 2);
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5916\u90e8\u94f0\u94fe\u529b\uff0cy", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.hgj5.Fz", 3);
    model.result("pg4").feature("glob1").setIndex("unit", "kN", 3);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5916\u90e8\u94f0\u94fe\u529b\uff0cz", 3);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u529b [kN]");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u540a\u673a\u9876\u7aef\u8f68\u8ff9");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(350);
    model.result("pg5").feature("ptgr1").set("expr", "w");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "v");
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").feature("ptgr1").set("linemarker", "circle");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("preserveaspect", true);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u5361\u8f66\u540a\u673a");

    model
         .description("\u8bb8\u591a\u5361\u8f66\u90fd\u914d\u5907\u6709\u540a\u673a\u7528\u6765\u88c5\u5378\u8d27\u7269\u3002\u6b64\u7c7b\u540a\u673a\u5177\u6709\u591a\u4e2a\u6db2\u538b\u7f38\u6765\u63a7\u5236\u540a\u673a\u7684\u8fd0\u52a8\uff0c\u5e76\u5305\u542b\u5176\u4ed6\u4e00\u4e9b\u673a\u6784\u3002\n\n\u672c\u4f8b\u5bf9\u540a\u673a\u6267\u884c\u521a\u4f53\u5206\u6790\uff0c\u4ee5\u786e\u5b9a\u8f7d\u8377\u5faa\u73af\u8fc7\u7a0b\u4e2d\u7684\u7f38\u4f53\u529b\u548c\u8f74\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("truck_mounted_crane.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
