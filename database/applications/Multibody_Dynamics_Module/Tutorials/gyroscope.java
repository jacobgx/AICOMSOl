/*
 * gyroscope.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:24 by COMSOL 6.3.0.290. */
public class gyroscope {

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

    model.component("comp1").label("\u7ec4\u4ef6 1\uff1a\u9640\u87ba\u4eea");

    model.param().set("omega", "350[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");
    model.param().set("theta0", "20[deg]");
    model.param().descr("theta0", "\u9640\u87ba\u7684\u503e\u89d2");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "gyroscope.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("wv1", "Wave");
    model.component("comp1").func("wv1").set("period", "0.5[s]");
    model.component("comp1").func("wv1").set("amplitude", 2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u6846\u67b6");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").set("Omega", new int[]{1, 1, 0});
    model.component("comp1").physics("mbd").feature("rd1").feature("pdr1").set("phi0", "wv1(t)");
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2")
         .label("\u521a\u6027\u6750\u6599\uff1a\u5916\u5e73\u8861\u73af");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(1);
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd3")
         .label("\u521a\u6027\u6750\u6599\uff1a\u5185\u5e73\u8861\u73af");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(4);
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd4")
         .label("\u521a\u6027\u6750\u6599\uff1a\u65cb\u8f6c\u5706\u76d8");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(3);
    model.component("comp1").physics("mbd").feature("rd4").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("rd4").feature("init1")
         .set("omega", new String[]{"0", "0", "omega"});

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");

    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").label("\u6846\u67b6-\u5916\u5e73\u8861\u73af");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj1").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2")
         .label("\u5916\u5e73\u8861\u73af-\u5185\u5e73\u8861\u73af");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("hgj2").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3")
         .label("\u5185\u5e73\u8861\u73af-\u65cb\u8f6c\u5706\u76d8");
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd4");
    model.component("comp1").physics("mbd").feature("hgj3").set("e", new int[]{0, 0, 1});

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("hgj1");
    model.nodeGroup("grp2").add("hgj2");
    model.nodeGroup("grp2").add("hgj3");
    model.nodeGroup("grp2").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("theta", "acos(mbd.hgj3.e1z)");
    model.component("comp1").variable("var1").descr("theta", "\u65cb\u8f6c\u5706\u76d8\u503e\u89d2");

    model.component("comp1").view("view1").set("showgrid", false);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u9640\u87ba\u4eea");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("param").setIndex("pname", "omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 350", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.0025,0.25)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "1e-4+9e-4*(omega==0)");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
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
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
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
    model.result("pg1").set("looplevel", new int[]{101, 1});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{101, 2});
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
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
    model.result().export("anim1").label("\u52a8\u753b\uff1a\u65e0\u65cb\u8f6c\u9640\u87ba\u4eea");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().create("anim2", "Animation");
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
    model.result().export("anim2").label("\u52a8\u753b\uff1a\u65cb\u8f6c\u9640\u87ba\u4eea");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("maxframes", 50);
    model.result().export("anim2").setIndex("singlelooplevel", 2, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u65cb\u8f6c\u5706\u76d8\u7684\u65b9\u5411");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"theta"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u65cb\u8f6c\u5706\u76d8\u503e\u89d2"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("markerpos", "interp");
    model.result("pg3").feature("glob1").set("markers", 25);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u65e0\u65cb\u8f6c", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "\u65cb\u8f6c", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u7ec4\u4ef6 2\uff1a\u9640\u87ba");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "gyroscope.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").create("del1", "Delete");
    model.component("comp2").geom("geom2").feature("del1").selection("input").init(3);
    model.component("comp2").geom("geom2").feature("del1").selection("input").set("imp1(1)", 1);
    model.component("comp2").geom("geom2").feature("del1").selection("input").set("imp1(2)", 1);
    model.component("comp2").geom("geom2").feature("del1").selection("input").set("imp1(4)", 1);
    model.component("comp2").geom("geom2").run("del1");
    model.component("comp2").geom("geom2").create("rot1", "Rotate");
    model.component("comp2").geom("geom2").feature("rot1").selection("input").set("imp1(3)");
    model.component("comp2").geom("geom2").feature("rot1").set("rot", "-theta0");
    model.component("comp2").geom("geom2").feature("rot1").set("pos", new double[]{0, 0, -0.0835});
    model.component("comp2").geom("geom2").feature("rot1").set("axistype", "x");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").coordSystem().create("sys3", "Rotated");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").coordSystem("sys3").set("angle", new String[]{"0", "-theta0", "0"});

    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat3").label("Aluminum");
    model.component("comp2").material("mat3").set("family", "aluminum");
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp2").material("mat3").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat3").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp2").material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat3").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp2").material("mat3").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp2").material("mat3").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp2").physics().create("mbd2", "MultibodyDynamics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/mbd2", false);

    model.component("comp2").physics("mbd2").create("rd1", "RigidDomain", 3);
    model.component("comp2").physics("mbd2").feature("rd1").selection().set(1);
    model.component("comp2").physics("mbd2").feature("rd1").set("InitialValueType", "locallyDefined");
    model.component("comp2").physics("mbd2").feature("rd1").feature("init1").set("coordinateSystem", "sys3");
    model.component("comp2").physics("mbd2").feature("rd1").feature("init1")
         .set("omega", new String[]{"0", "0", "omega"});
    model.component("comp2").physics("mbd2").feature("rd1").feature("init1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp2").physics("mbd2").feature("rd1").feature("init1").set("EntityLevel", "Point");
    model.component("comp2").physics("mbd2").feature("rd1").feature("init1").feature("crp1").selection().set(23);
    model.component("comp2").physics("mbd2").feature("rd1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1").set("EntityLevel", "Point");
    model.component("comp2").physics("mbd2").feature("rd1").feature("pdr1").feature("crp1").selection().set(23);

    model.component("comp2").mesh("mesh2").contribute("geom/detail", false);
    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").physics("mbd2").create("gacc1", "GravityAcceleration", -1);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("th", "atan2(y,x)");
    model.component("comp2").variable("var2").descr("th", "\u8fdb\u52a8\u89d2");
    model.component("comp2").variable("var2").set("vp", "cos(th+pi/2)*mbd2.u_tX+sin(th+pi/2)*mbd2.u_tY");
    model.component("comp2").variable("var2").descr("vp", "\u8fdb\u52a8\u901f\u5ea6");
    model.component("comp2").variable("var2").set("vn", "cos(th)*mbd2.u_tX+sin(th)*mbd2.u_tY");
    model.component("comp2").variable("var2").descr("vn", "\u7ae0\u52a8\u901f\u5ea6");

    model.component("comp2").view("view2").set("showgrid", false);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", false);
    model.study("std2").feature("time").setSolveFor("/physics/mbd2", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u9640\u87ba");
    model.study("std2").feature("time").set("tlist", "range(0,0.007,1.4)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol5").feature("t1").set("maxstepbdf", "1e-4");

    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4f4d\u79fb (mbd2)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 201, 0);
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
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 201, 0);
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
    model.result("pg4").create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").selection().set(36);
    model.result("pg4").feature("pttraj1").set("linetype", "tube");
    model.result("pg4").feature("pttraj1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("pttraj1").feature("col1").set("expr", "t");
    model.result("pg4").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 126, 0);
    model.result("pg4").run();
    model.result().export().create("anim3", "Animation");
    model.result().export("anim3").set("fontsize", "9");
    model.result().export("anim3").set("colortheme", "globaltheme");
    model.result().export("anim3").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim3").set("background", "color");
    model.result().export("anim3").set("gltfincludelines", "on");
    model.result().export("anim3").set("title1d", "on");
    model.result().export("anim3").set("legend1d", "on");
    model.result().export("anim3").set("logo1d", "on");
    model.result().export("anim3").set("options1d", "on");
    model.result().export("anim3").set("title2d", "on");
    model.result().export("anim3").set("legend2d", "on");
    model.result().export("anim3").set("logo2d", "on");
    model.result().export("anim3").set("options2d", "off");
    model.result().export("anim3").set("title3d", "on");
    model.result().export("anim3").set("legend3d", "on");
    model.result().export("anim3").set("logo3d", "on");
    model.result().export("anim3").set("options3d", "off");
    model.result().export("anim3").set("axisorientation", "on");
    model.result().export("anim3").set("grid", "on");
    model.result().export("anim3").set("axes1d", "on");
    model.result().export("anim3").set("axes2d", "on");
    model.result().export("anim3").set("showgrid", "on");
    model.result().export("anim3").label("\u52a8\u753b\uff1a\u9640\u87ba");
    model.result().export("anim3").set("target", "player");
    model.result().export("anim3").set("plotgroup", "pg4");
    model.result().export("anim3").set("maxframes", 50);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5c16\u7aef\u8f68\u8ff9\uff08xy \u5e73\u9762\uff09");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("preserveaspect", true);
    model.result("pg6").set("manualgrid", true);
    model.result("pg6").set("xspacing", 0.01);
    model.result("pg6").set("yspacing", 0.01);
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(36);
    model.result("pg6").feature("ptgr1").set("expr", "y");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "x");
    model.result("pg6").feature("ptgr1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("preserveaspect", false);
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", -0.085);
    model.result("pg6").set("xmax", 0.085);
    model.result("pg6").set("ymin", -0.065);
    model.result("pg6").set("ymax", 0.065);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("axislimits", false);
    model.result("pg6").set("preserveaspect", true);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5c16\u7aef\u901f\u5ea6\uff08xy \u5e73\u9762\uff09");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u901f\u5ea6 (m/s)");
    model.result("pg7").set("preserveaspect", false);
    model.result("pg7").set("manualgrid", false);
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "vp");
    model.result("pg7").feature("ptgr1").set("descr", "\u8fdb\u52a8\u901f\u5ea6");
    model.result("pg7").feature("ptgr1").set("xdata", "solution");
    model.result("pg7").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u8fdb\u52a8", 0);
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("expr", "vn");
    model.result("pg7").feature("ptgr2").set("descr", "\u7ae0\u52a8\u901f\u5ea6");
    model.result("pg7").feature("ptgr2").setIndex("legends", "\u7ae0\u52a8", 0);
    model.result("pg7").run();
    model.result("pg1").run();

    model.title("\u9640\u87ba\u6548\u5e94\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4e3a\u673a\u68b0\u9640\u87ba\u5efa\u6a21\uff0c\u5750\u6807\u7cfb\u7684\u65cb\u8f6c\u4f7f\u65cb\u8f6c\u76d8\u4e0a\u4ea7\u751f\u4e86\u5916\u8f6c\u77e9\uff0c\u5176\u4e2d\u5206\u6790\u4e86\u65cb\u8f6c\u76d8\u7684\u54cd\u5e94\uff0c\u8fd8\u6bd4\u8f83\u4e86\u65cb\u8f6c\u76d8\u5728\u65cb\u8f6c\u548c\u4e0d\u65cb\u8f6c\u65f6\uff0c\u5176\u65b9\u5411\u7684\u53d8\u5316\u60c5\u51b5\u3002\u5728\u6a21\u578b\u7684\u7b2c\u4e8c\u90e8\u5206\uff0c\u5206\u6790\u4e86\u9640\u87ba\u4e0a\u7684\u5916\u8f6c\u77e9\u5f15\u8d77\u7684\u8fdb\u52a8\u548c\u7ae0\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("gyroscope.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
