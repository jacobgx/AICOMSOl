/*
 * gear_rattle_with_bearing_misalignment.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:26 by COMSOL 6.3.0.290. */
public class gear_rattle_with_bearing_misalignment {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials,_Transmission");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.param().label("\u53c2\u6570\uff1a\u901a\u7528");
    model.param().set("omega0", "20[rad/s]");
    model.param().descr("omega0", "\u5e73\u5747\u89d2\u901f\u5ea6");
    model.param().set("T0", "100[N*m]");
    model.param().descr("T0", "\u8d1f\u8f7d\u626d\u77e9");
    model.param().set("T", "2*pi/omega0");
    model.param().descr("T", "\u65f6\u95f4\u5468\u671f");
    model.param().set("isMisaligned", "0");
    model.param().descr("isMisaligned", "\u4e0d\u5bf9\u4e2d");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u9f7f\u8f6e");
    model.param("par2").set("N1", "20");
    model.param("par2").descr("N1", "\u9f7f\u8f6e 1 \u7684\u9f7f\u6570");
    model.param("par2").set("N2", "30");
    model.param("par2").descr("N2", "\u9f7f\u8f6e 2 \u7684\u9f7f\u6570");
    model.param("par2").set("dp1", "100[mm]");
    model.param("par2").descr("dp1", "\u9f7f\u8f6e 1 \u7684\u8282\u5706\u76f4\u5f84");
    model.param("par2").set("dp2", "150[mm]");
    model.param("par2").descr("dp2", "\u9f7f\u8f6e 2 \u7684\u8282\u5706\u76f4\u5f84");
    model.param("par2").set("gr", "N2/N1");
    model.param("par2").descr("gr", "\u9f7f\u8f6e\u6bd4");
    model.param("par2").set("rc", "0.5*(dp1+dp2)");
    model.param("par2").descr("rc", "\u4e2d\u5fc3\u8ddd");
    model.param("par2").set("bl", "1e-3[m]");
    model.param("par2").descr("bl", "\u9f7f\u9699");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u6eda\u5b50\u8f74\u627f");
    model.param("par3").set("db", "1.33[mm]");
    model.param("par3").descr("db", "\u7403\u5f84");
    model.param("par3").set("dp", "21.33[mm]");
    model.param("par3").descr("dp", "\u8282\u5706\u76f4\u5f84");
    model.param("par3").set("rin", "0.53*db");
    model.param("par3").descr("rin", "\u5185\u5708\u534a\u5f84");
    model.param("par3").set("rout", "0.53*db");
    model.param("par3").descr("rout", "\u5916\u5708\u534a\u5f84");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Omega", "omega0*(1+0.05*sin(10*omega0*t))");
    model.component("comp1").variable("var1").descr("Omega", "\u89d2\u901f\u5ea6");

    model.geom()
         .load(new String[]{"part1"}, "Multibody_Dynamics_Module\\3D\\External_Gears\\spur_gear.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n", "N1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dp", "dp1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "lsr", 3);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "egy", 1);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "egz", 0);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "n", "N2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dp", "dp2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dhr", "0.2/gr");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wgr", "0.2/gr");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "lsr", "3/gr");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "xc", "rc");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "egy", 1);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "egz", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "th", "360[deg]/2/N2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", -0.13);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().duplicate("wp2", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", 0.13);
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("quicky", -0.11);
    model.component("comp1").geom("geom1").feature().duplicate("wp4", "wp3");
    model.component("comp1").geom("geom1").feature("wp4").set("quicky", 0.11);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("pi1", "pi2");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature().duplicate("par2", "par1");
    model.component("comp1").geom("geom1").runPre("par2");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").feature().duplicate("par3", "par2");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("par3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").run("par3");
    model.component("comp1").geom("geom1").feature().duplicate("par4", "par3");
    model.component("comp1").geom("geom1").feature("par4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("par4").selection("input").set("par3");
    model.component("comp1").geom("geom1").run("par4");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup().create("Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
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
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1").selection().set(86);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "Omega");
    model.component("comp1").physics("mbd").create("rrb1", "RadialRollerBearing", 2);
    model.component("comp1").physics("mbd").feature("rrb1").selection().set(87, 88, 118, 119);
    model.component("comp1").physics("mbd").feature("rrb1").set("bearingAxis", "yAxis");
    model.component("comp1").physics("mbd").feature("rrb1").set("yDirection", "userDef");
    model.component("comp1").physics("mbd").feature("rrb1").set("e_aux", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature("rrb1").set("d_ball", "db");
    model.component("comp1").physics("mbd").feature("rrb1").set("d_pitch", "dp");
    model.component("comp1").physics("mbd").feature("rrb1").set("r_in", "rin");
    model.component("comp1").physics("mbd").feature("rrb1").set("r_out", "rout");
    model.component("comp1").physics("mbd").feature().duplicate("rrb2", "rrb1");
    model.component("comp1").physics("mbd").feature("rrb2").selection().set(97, 98, 134, 135);
    model.component("comp1").physics("mbd").feature().duplicate("rrb3", "rrb2");
    model.component("comp1").physics("mbd").feature("rrb3").selection().set(343, 344, 378, 385);
    model.component("comp1").physics("mbd").feature().duplicate("rrb4", "rrb3");
    model.component("comp1").physics("mbd").feature("rrb4").selection().set(353, 354, 382, 389);
    model.component("comp1").physics("mbd").feature("rrb2").create("mlgn1", "Misalignment", 2);
    model.component("comp1").physics("mbd").feature("rrb2").feature("mlgn1").set("th0y", "0.1[deg]*isMisaligned");
    model.component("comp1").physics("mbd").create("spg1", "SpurGear", 3);
    model.component("comp1").physics("mbd").feature("spg1").selection().set(1);
    model.component("comp1").physics("mbd").feature("spg1").set("nt", "N1");
    model.component("comp1").physics("mbd").feature("spg1").set("dp", "dp1");
    model.component("comp1").physics("mbd").feature("spg1").set("alpha", "25[deg]");
    model.component("comp1").physics("mbd").feature("spg1").set("eg", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("spg1").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature().duplicate("spg2", "spg1");
    model.component("comp1").physics("mbd").feature("spg2").selection().set(7);
    model.component("comp1").physics("mbd").feature("spg2").set("nt", "N2");
    model.component("comp1").physics("mbd").feature("spg2").set("dp", "dp2");
    model.component("comp1").physics("mbd").feature("spg2").set("xc", new String[]{"rc", "0", "0"});
    model.component("comp1").physics("mbd").create("grp1", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp1").set("Wheel", "spg1");
    model.component("comp1").physics("mbd").feature("grp1").set("Pinion", "spg2");
    model.component("comp1").physics("mbd").feature("grp1").set("IncludeBacklash", true);
    model.component("comp1").physics("mbd").feature("grp1").set("ContactForceComputation", "PenaltyMethod");
    model.component("comp1").physics("mbd").feature("grp1").set("pc", "1e8");
    model.component("comp1").physics("mbd").feature("grp1").feature("bcl1").set("bl", "bl");
    model.component("comp1").physics("mbd").feature("grp1").feature("bcl1")
         .set("pb", "((1[1/ms])^2)*mbd.grp1.Ie*200");
    model.component("comp1").physics("mbd").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("mbd").feature("rig1").selection().set(359);
    model.component("comp1").physics("mbd").feature("rig1").create("rm1", "RigidBodyMoment", -1);
    model.component("comp1").physics("mbd").feature("rig1").feature("rm1")
         .set("Mt", new String[]{"0", "T0*(t>T/8)", "0"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(86, 342);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3, 4, 5, 6, 8, 9, 10, 11, 12);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T/20000,T/4) range(T/4+T/10000,T/10000,T)");
    model.study("std1").create("batsw", "BatchSweep");
    model.study("std1").feature("batsw").set("useaccumtable", false);
    model.study("std1").feature("batsw").set("clearmesh", false);
    model.study("std1").feature("batsw").set("clearsol", false);
    model.study("std1").feature("batsw").set("maxallow", 2);
    model.study("std1").feature("batsw").setIndex("pname", "bl", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "m", 0);
    model.study("std1").feature("batsw").setIndex("pname", "bl", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "m", 0);
    model.study("std1").feature("batsw").setIndex("pname", "isMisaligned", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "0 1", 0);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphaz").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_ur").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphaz").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_ur").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphay").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphaz").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphaz").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphay").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphay").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphay").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_rot").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_ur").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_ur").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_th").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_disp").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_rot").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_disp").set("resscalemethod", "parent");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_rot").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_rot").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphaz").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_disp").set("scaleval", "0.004371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_ur").set("scaleval", "1e-2*0.4371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphaz").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_ur").set("scaleval", "1e-2*0.4371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphay").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphaz").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphaz").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphay").set("scaleval", "1e-4");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphay").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_rot").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphay").set("scaleval", "1e-4");
    model.sol("sol1").feature("v1").feature("comp1_mbd_att_rot").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.4371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_ur").set("scaleval", "1e-2*0.4371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_ur").set("scaleval", "1e-2*0.4371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_jnt_disp").set("scaleval", "0.004371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_gr_disp").set("scaleval", "0.004371488899048227");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_th").set("scaleval", "0.1");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rig_disp").set("scaleval", "0.004371488899048227");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,T/20000,T/4) range(T/4+T/10000,T/10000,T)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("rtol", 0.001);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolglobalmethod", "scaled");
    model.sol("sol1").feature("t1").set("atolglobalfactor", 0.1);
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("eventtol", 0.01);
    model.sol("sol1").feature("t1").set("reacf", true);
    model.sol("sol1").feature("t1").set("storeudot", true);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("minorder", 1);
    model.sol("sol1").feature("t1").set("rescaleafterinitbw", false);
    model.sol("sol1").feature("t1").set("bwinitstepfrac", "0.001");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("fc1").set("ntermconst", "tol");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch().create("b1", "Batch");
    model.batch("b1").study("std1");
    model.batch("b1").create("so1", "Solutionseq");
    model.batch("b1").feature("so1").set("seq", "sol1");
    model.batch("b1").feature("so1").set("store", "on");
    model.batch("b1").feature("so1").set("clear", "on");
    model.batch("b1").feature("so1").set("psol", "none");
    model.batch("b1").set("batchfile", "batchmodel.mph");
    model.batch("b1").set("batchfileactive", "off");
    model.batch("b1").set("batchdir", "C:/tmp/testusr/batchdir");
    model.batch("b1").set("paramfilename", "on");
    model.batch("b1").set("synchsolutions", "on");
    model.batch("b1").set("clearsynchdata", "on");
    model.batch("b1").set("savemodelaftersynch", "off");
    model.batch("b1").attach("std1");
    model.batch("b1").set("control", "batsw");
    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("jo1", "Jobseq");
    model.batch("p1").feature("jo1").set("seq", "b1");
    model.batch("p1").set("pname", new String[]{"isMisaligned"});
    model.batch("p1").set("plistarr", new String[]{"0 1"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "batsw");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);
    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("b1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");
    model.batch("b1").feature("daDef").run();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 12501, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 12501, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("defaultPlotID", "displacement");
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
    model.result("pg2").setIndex("looplevel", 12501, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 12501, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("defaultPlotID", "velocity");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u4f53");
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("unit", "1");
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
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
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b (mbd)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.SYY");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", "-3e7");
    model.result("pg3").feature("surf1").set("rangecolormax", "3e7");
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(1, 7);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "gray");
    model.result("pg3").feature("surf2").set("data", "dset3");
    model.result("pg3").feature("surf2").set("solutionparams", "parent");
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"mbd.rrb1.fbx", "mbd.rrb1.fby", "mbd.rrb1.fbz"});
    model.result("pg3").feature("arws1")
         .set("descr", "\u8f74\u627f\u529b\u5206\u5e03 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").feature("arws1").set("placement", "elements");
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", "2E-4");
    model.result("pg3").feature("arws1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("def1").set("expr", new String[]{"mbd.rrb1.u_cage", "v", "w"});
    model.result("pg3").feature("arws1").feature("def1").setIndex("expr", "mbd.rrb1.v_cage", 1);
    model.result("pg3").feature("arws1").feature("def1").setIndex("expr", "mbd.rrb1.w_cage", 2);
    model.result("pg3").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arws2", "arws1");
    model.result("pg3").run();
    model.result("pg3").feature("arws2").set("inheritplot", "arws1");
    model.result("pg3").feature("arws2").set("titletype", "none");
    model.result("pg3").feature("arws2").setIndex("expr", "mbd.rrb2.fbx", 0);
    model.result("pg3").feature("arws2").setIndex("expr", "mbd.rrb2.fby", 1);
    model.result("pg3").feature("arws2").setIndex("expr", "mbd.rrb2.fbz", 2);
    model.result("pg3").run();
    model.result("pg3").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.u_cage", 0);
    model.result("pg3").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.v_cage", 1);
    model.result("pg3").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.w_cage", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arws3", "arws2");
    model.result("pg3").run();
    model.result("pg3").feature("arws3").setIndex("expr", "mbd.rrb3.fbx", 0);
    model.result("pg3").feature("arws3").setIndex("expr", "mbd.rrb3.fby", 1);
    model.result("pg3").feature("arws3").setIndex("expr", "mbd.rrb3.fbz", 2);
    model.result("pg3").run();
    model.result("pg3").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.u_cage", 0);
    model.result("pg3").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.v_cage", 1);
    model.result("pg3").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.w_cage", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arws4", "arws3");
    model.result("pg3").run();
    model.result("pg3").feature("arws4").setIndex("expr", "mbd.rrb4.fbx", 0);
    model.result("pg3").feature("arws4").setIndex("expr", "mbd.rrb4.fby", 1);
    model.result("pg3").feature("arws4").setIndex("expr", "mbd.rrb4.fbz", 2);
    model.result("pg3").run();
    model.result("pg3").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.u_cage", 0);
    model.result("pg3").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.v_cage", 1);
    model.result("pg3").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.w_cage", 2);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u8868\u9762\uff1a\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0cYY \u5206\u91cf (N/m<sup>2</sup>)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u89d2\u901f\u5ea6");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"Omega"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u89d2\u901f\u5ea6"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u4f20\u52a8\u8f74", 0);
    model.result("pg4").feature("glob1").set("expr", new String[]{"Omega", "mbd.grp1.tht_wh"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u4f20\u52a8\u8f74", "\u5927\u9f7f\u8f6e\u89d2\u901f\u5ea6"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u8f6e", 1);
    model.result("pg4").feature("glob1").set("expr", new String[]{"Omega", "mbd.grp1.tht_wh", "mbd.grp1.tht_pn"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u4f20\u52a8\u8f74", "\u8f6e", "\u5c0f\u9f7f\u8f6e\u89d2\u901f\u5ea6"});
    model.result("pg4").feature("glob1").setIndex("expr", "-mbd.grp1.tht_pn", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "\u5c0f\u9f7f\u8f6e (-ve)", 2);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u4f20\u52a8\u8f74", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u8f6e", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "\u5c0f\u9f7f\u8f6e (-ve)", 2);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "last", 1);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u8f74\u627f\u529b");
    model.result("pg5").setIndex("looplevelinput", "first", 1);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("descr", new String[]{});
    model.result("pg5").feature("glob1").set("unit", new String[]{});
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.rrb2.Fsx", "mbd.rrb2.Fsy", "mbd.rrb2.Fsz"});
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b (rrb2)\uff0cx \u5206\u91cf", 0);
    model.result("pg5").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b (rrb2)\uff0cy \u5206\u91cf", 1);
    model.result("pg5").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b (rrb2)\uff0cz \u5206\u91cf", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8f74\u4e0a\u7684\u8f74\u627f\u529b (N)");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "last", 1);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u8f74\u627f\u529b\u77e9");
    model.result("pg6").setIndex("looplevelinput", "first", 1);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").set("unit", new String[]{});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.rrb2.Msx", "mbd.rrb2.Msy", "mbd.rrb2.Msz"});
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b\u77e9 (rrb2)\uff0cx \u5206\u91cf", 0);
    model.result("pg6").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b\u77e9 (rrb2)\uff0cy \u5206\u91cf", 1);
    model.result("pg6").feature("glob1")
         .setIndex("legends", "\u8f74\u4e0a\u7684\u529b\u77e9 (rrb2)\uff0cz \u5206\u91cf", 2);
    model.result("pg6").run();
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u8f74\u4e0a\u7684\u8f74\u627f\u529b\u77e9 (N*m)");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "last", 1);
    model.result("pg6").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u8f6e\u7684 Y \u52a0\u901f\u5ea6");
    model.result("pg7").set("data", "cpt1");
    model.result("pg7").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg7").setIndex("looplevelindices", "range(6000,1,12501)", 0);
    model.result("pg7").setIndex("looplevelinput", "first", 1);
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("expr", "vtt");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u8f6e\u7684 Y \u52a0\u901f\u5ea6\uff08\u9891\u8c31\uff09");
    model.result("pg8").setIndex("looplevelinput", "first", 1);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("xdata", "fourier");
    model.result("pg8").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg8").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg8").feature("ptgr1").set("freqmax", 2000);
    model.result("pg8").feature("ptgr1").set("linewidth", 2);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "last", 1);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset2");
    model.result("pg9").label("\u8f74\u627f 2 \u5904\u7684\u8f6c\u5b50\u503e\u659c");
    model.result("pg9").setIndex("looplevelinput", "first", 1);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.rrb2.alphay", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "mbd.rrb2.alphaz", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg9").feature("glob1").set("linewidth", 2);
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u8f6c\u5b50\u503e\u659c\uff0c\u5c40\u90e8 y \u65b9\u5411", 0);
    model.result("pg9").feature("glob1")
         .setIndex("legends", "\u8f6c\u5b50\u503e\u659c\uff0c\u5c40\u90e8 z \u65b9\u5411", 1);
    model.result("pg9").run();
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u8f6c\u5b50\u503e\u659c (deg)");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevelinput", "last", 1);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset2");
    model.result("pg10").label("\u9f7f\u8f6e\u556e\u5408\u63a5\u89e6\u529b");
    model.result("pg10").setIndex("looplevelinput", "first", 1);
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").set("expr", new String[]{"mbd.grp1.Fc"});
    model.result("pg10").feature("glob1").set("descr", new String[]{"\u63a5\u89e6\u70b9\u7684\u529b"});
    model.result("pg10").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg10").feature("glob1").set("linewidth", 2);
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "Misaligned=eval(isMisaligned)");
    model.result("pg10").set("showlegends", false);
    model.result("pg10").run();
    model.result("pg10").setIndex("looplevelinput", "last", 1);
    model.result("pg10").run();
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
    model.result().export("anim1").set("maxframes", 50);
    model.result().export("anim1").setIndex("singlelooplevel", 2, 1);

    model.batch("b1").feature("daDef").set("operation", "clearalldata");
    model.batch("b1").feature("daDef").run();

    model.result("pg3").run();

    model.title("\u9f7f\u8f6e\u6572\u51fb\u548c\u8f74\u627f\u4e0d\u5bf9\u4e2d\u5f15\u8d77\u7684\u8f74\u632f\u52a8");

    model
         .description("\u5728\u53d8\u901f\u7bb1\u4e2d\uff0c\u7531\u4e8e\u9f7f\u8f6e\u6572\u51fb\u548c\u8f74\u627f\u4e0d\u5bf9\u4e2d\u5f15\u8d77\u7684\u632f\u52a8\u662f\u4f17\u6240\u5468\u77e5\u7684\u566a\u58f0\u6e90\u3002\u672c\u4f8b\u5206\u6790\u901a\u8fc7\u4e00\u5bf9\u9f7f\u8f6e\u8fde\u63a5\u7684\u4e24\u6839\u8f74\uff0c\u8f74\u7684\u4e24\u7aef\u7531\u6eda\u5b50\u8f74\u627f\u652f\u6491\u3002\u6700\u521d\uff0c\u4ece\u52a8\u8f74\u88ab\u5378\u8f7d\uff0c\u9a71\u52a8\u8f74\u4ee5\u4e0d\u540c\u7684\u901f\u5ea6\u65cb\u8f6c\u3002\u7531\u4e8e\u9f7f\u8f6e\u4e2d\u5b58\u5728\u9f7f\u9699\uff0c\u9f7f\u8f6e\u556e\u5408\u53d8\u4e3a\u95f4\u6b47\u6027\u7684\uff0c\u4ece\u800c\u5f15\u8d77\u8f74\u632f\u52a8\u3002\u4e00\u6bb5\u65f6\u95f4\u540e\uff0c\u4ece\u52a8\u8f74\u4f1a\u52a0\u8f7d\u4e00\u4e2a\u626d\u77e9\uff0c\u4f7f\u9f7f\u8f6e\u556e\u5408\u8d8b\u4e8e\u5e73\u7a33\u3002\u4e3a\u4e86\u5206\u6790\u8f74\u627f\u4e0d\u5bf9\u4e2d\u5bf9\u8f6c\u5b50\u632f\u52a8\u7684\u5f71\u54cd\uff0c\u672c\u4f8b\u5bf9\u4e24\u79cd\u60c5\u51b5\u8fdb\u884c\u77ac\u6001\u5206\u6790\u3002\u5728\u7b2c\u4e00\u79cd\u60c5\u51b5\u4e0b\uff0c\u6240\u6709\u8f74\u627f\u90fd\u4e0e\u8f74\u5bf9\u9f50\uff1b\u5728\u7b2c\u4e8c\u79cd\u60c5\u51b5\u4e0b\uff0c\u5176\u4e2d\u4e00\u4e2a\u8f74\u627f\u53d1\u751f\u5c0f\u89d2\u5ea6\u504f\u5dee\u3002\u7ed3\u679c\u8868\u660e\uff0c\u5b58\u5728\u4e0d\u5bf9\u4e2d\u65f6\uff0c\u8f74\u7684\u626d\u8f6c\u548c\u8f74\u5411\u632f\u52a8\u8f83\u5927\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("gear_rattle_with_bearing_misalignment.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
