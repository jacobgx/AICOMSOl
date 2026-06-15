/*
 * radial_cam_follower.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:24 by COMSOL 6.3.0.290. */
public class radial_cam_follower {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rf", "(0.025/3)[m]", "\u4ece\u52a8\u4ef6\u534a\u5f84");
    model.param().set("k", "5[kN/m]", "\u5f39\u7c27\u521a\u5ea6");
    model.param().set("N", "1200", "\u51f8\u8f6e\u8f74\u8f6c\u901f");
    model.param().set("omega", "(2*pi*N/60)[rad/s]", "\u51f8\u8f6e\u8f74\u89d2\u901f\u5ea6");
    model.param().set("T", "2*pi/omega", "\u65f6\u95f4\u5468\u671f");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "radial_cam_follower.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "T/40");
    model.func("step1").set("smooth", "T/20");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(89);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").label("\u51f8\u8f6e\u8868\u9762");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("adj1").set("outputdim", 1);
    model.component("comp1").selection("adj1").label("\u51f8\u8f6e\u8868\u9762\uff1a\u76f8\u90bb\u8fb9");

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

    model.component("comp1").physics("mbd").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u51f8\u8f6e");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(5);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u4ece\u52a8\u4ef6");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(7);
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u6447\u81c2");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(3);
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u9600\u95e8");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(1);
    model.component("comp1").physics("mbd").create("rd5", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd5")
         .label("\u521a\u6027\u6750\u6599\uff1a\u4ece\u52a8\u4ef6\u5bfc\u627f");
    model.component("comp1").physics("mbd").feature("rd5").selection().set(6);
    model.component("comp1").physics("mbd").create("rd6", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd6").label("\u521a\u6027\u6750\u6599\uff1a\u9500");
    model.component("comp1").physics("mbd").feature("rd6").selection().set(4);
    model.component("comp1").physics("mbd").create("rd7", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd7")
         .label("\u521a\u6027\u6750\u6599\uff1a\u9600\u95e8\u5bfc\u627f");
    model.component("comp1").physics("mbd").feature("rd7").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd5").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd6").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd7").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("cfc1", "CamFollower", -1);
    model.component("comp1").physics("mbd").feature("cfc1").selection("bndCam").named("sel1");
    model.component("comp1").physics("mbd").feature("cfc1").selection("PntFollower").set(148, 164);
    model.component("comp1").physics("mbd").feature("cfc1").set("X_off_bnd", "rf");
    model.component("comp1").physics("mbd").feature("cfc1").set("ConnectionForce", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(108, 128);
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd6");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjp1").selection().set(94, 100);
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("prj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("prj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("prj1").feature("cjp1").selection().set(133, 147);
    model.component("comp1").physics("mbd").feature().duplicate("prj2", "prj1");
    model.component("comp1").physics("mbd").feature("prj2").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature("prj2").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("prj2").feature("cjp1").selection().set(35, 49);
    model.component("comp1").physics("mbd").create("slj1", "SlotJoint", -1);
    model.component("comp1").physics("mbd").feature("slj1").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("slj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("slj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("slj1").feature("cjp1").selection().set(151, 162);
    model.component("comp1").physics("mbd").feature().duplicate("slj2", "slj1");
    model.component("comp1").physics("mbd").feature("slj2").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("slj2").feature("cjp1").selection().set(58, 62);
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "omega*step1(t)");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("WeakConstraints", true);
    model.component("comp1").physics("mbd").feature("prj2").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("prj2").feature("sd1").set("k_u", "k");

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").add("rd5");
    model.nodeGroup("grp1").add("rd6");
    model.nodeGroup("grp1").add("rd7");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("cfc1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");
    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("cfc1");
    model.nodeGroup("grp3").add("prj1");
    model.nodeGroup("grp3").add("prj2");
    model.nodeGroup("grp3").label("\u68f1\u67f1\u5173\u8282");
    model.nodeGroup().create("grp4", "Physics", "mbd");
    model.nodeGroup("grp4").placeAfter("cfc1");
    model.nodeGroup("grp4").add("slj1");
    model.nodeGroup("grp4").add("slj2");
    model.nodeGroup("grp4").label("\u69fd\u5173\u8282");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hcurve", 0.015);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(199);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T/400,T/2)");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "rf", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "rf", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "k", 0);
    model.study("std1").feature("time").setIndex("plistarr", "5 10 20 30", 0);
    model.study("std1").feature("time").setIndex("punit", "kN/m", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_mbd_cfc1_F").set("scaleval", "1e9");
    model.sol("sol1").feature("v1").feature("comp1_mbd_hgj1_pm1_RM").set("scaleval", "1e7");

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
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u4ece\u52a8\u4ef6\u901f\u5ea6");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.prj1.u"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg3").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "mbd.hgj1.th");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u76f8\u5bf9\u65cb\u8f6c");
    model.result("pg3").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg3").feature("glob1").set("xdatadescractive", true);
    model.result("pg3").feature("glob1").set("xdatadescr", "\u51f8\u8f6e\u65cb\u8f6c");
    model.result("pg3").feature("glob1").set("linestyle", "dashed");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u4f4d\u79fb", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("expr", new String[]{"mbd.prj1.u_t"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u76f8\u5bf9\u901f\u5ea6"});
    model.result("pg3").feature("glob2").set("unit", new String[]{"m/s"});
    model.result("pg3").feature("glob2").set("plotonsecyaxis", false);
    model.result("pg3").feature("glob2").set("linestyle", "solid");
    model.result("pg3").feature("glob2").setIndex("legends", "\u901f\u5ea6", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u4ece\u52a8\u4ef6\u52a0\u901f\u5ea6");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("expr", new String[]{"mbd.prj1.u_tt"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u76f8\u5bf9\u52a0\u901f\u5ea6"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("glob2").set("unit", new String[]{"m/s^2"});
    model.result("pg4").feature("glob2").setIndex("legends", "\u52a0\u901f\u5ea6", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8fde\u63a5\u529b");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").setIndex("looplevelindices", "range(25,1,201)", 0);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.cfc1.F"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u8fde\u63a5\u529b"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "mbd.hgj1.th");
    model.result("pg5").feature("glob1").set("xdatadescr", "\u76f8\u5bf9\u65cb\u8f6c");
    model.result("pg5").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg5").feature("glob1").set("xdatadescractive", true);
    model.result("pg5").feature("glob1").set("xdatadescr", "\u51f8\u8f6e\u65cb\u8f6c");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6240\u9700\u626d\u77e9");
    model.result("pg6").set("legendpos", "upperright");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj1.pm1.RM"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\u77e9"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg6").run();
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
    model.result("pg2").run();

    model.title("\u57fa\u4e8e\u5f84\u5411\u51f8\u8f6e\u7684\u9600\u95e8\u5f00\u5ea6\u673a\u6784\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5177\u6709\u6447\u81c2\u548c\u5f84\u5411\u51f8\u8f6e\u7684\u5f39\u7c27\u5f0f\u9600\u95e8\u5f00\u5ea6\u673a\u6784\u3002\u7cfb\u7edf\u7684\u6240\u6709\u7ec4\u4ef6\u5747\u4f5c\u4e3a\u521a\u4f53\u5efa\u6a21\uff0c\u5e76\u901a\u8fc7\u68f1\u67f1\u3001\u94f0\u94fe\u548c\u69fd\u5173\u8282\u8fde\u63a5\u3002\u51f8\u8f6e-\u4ece\u52a8\u4ef6\u8fde\u63a5\u4ee5\u53ca\u5176\u4ed6\u5173\u8282\u8fde\u63a5\u5747\u901a\u8fc7\u201c\u591a\u4f53\u52a8\u529b\u5b66\u201d\u63a5\u53e3\u7684\u5185\u7f6e\u8282\u70b9\u5efa\u6a21\u3002\n\n\u5e76\u5bf9\u5404\u79cd\u5f39\u7c27\u521a\u5ea6\u503c\u6267\u884c\u4e86\u77ac\u6001\u5206\u6790\u3002\u6a21\u578b\u8f93\u51fa\u5305\u62ec\u4ece\u52a8\u4ef6\u901f\u5ea6\u3001\u4ece\u52a8\u4ef6\u52a0\u901f\u5ea6\u3001\u51f8\u8f6e-\u4ece\u52a8\u4ef6\u8fde\u63a5\u529b\u4ee5\u53ca\u6240\u9700\u7684\u626d\u77e9\u7b49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("radial_cam_follower.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
