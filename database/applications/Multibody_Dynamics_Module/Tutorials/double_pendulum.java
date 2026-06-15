/*
 * double_pendulum.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:23 by COMSOL 6.3.0.290. */
public class double_pendulum {

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

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1, 0.5, 10});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.3);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.5, 0, 9.5});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.3);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 1.25);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0.5, -0.75, 0.5});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{10, 0.5, 1});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, -0.625, 0});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("copy1");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl2", "dif1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1, 2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Wp", "intop1(mbd.rho*g_const*w)");
    model.component("comp1").variable("var1").descr("Wp", "\u52bf\u80fd");

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

    model.component("comp1").physics("mbd").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1").selection().set(19, 20, 24, 25);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "att1");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj1").set("JointForcesAndMoments", "DoNotCompute");
    model.component("comp1").physics("mbd").create("att2", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att2").selection().set(16, 17, 22, 23);
    model.component("comp1").physics("mbd").create("att3", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att3").selection().set(6, 7, 8, 9);
    model.component("comp1").physics("mbd").create("hgj2", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "att2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "att3");
    model.component("comp1").physics("mbd").feature("hgj2").set("e", new int[]{0, 1, 0});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.study("std1").feature("time").set("tlist", "range(0,0.02,1.5)");
    model.study("std1").label("\u7814\u7a76\uff1a\u57fa\u672c");
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
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").selection().set(14, 21);
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature("pttraj1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u76f8\u5bf9\u8f6c\u52a8");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.hgj2.th"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u65cb\u8f6c"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u76f8\u5bf9\u8f6c\u52a8", 0);
    model.result("pg3").feature("glob1").set("titletype", "none");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5173\u8282\u529b");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx", "mbd.hgj2.Fy"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\uff0cy \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.hgj2.Fx", "mbd.hgj2.Fy", "mbd.hgj2.Fz"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\uff0cy \u5206\u91cf", "\u5173\u8282\u529b\uff0cz \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5173\u8282\u529b (N)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();

    model.component("comp1").physics("mbd").feature("hgj2").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("hgj2").set("xc", new double[]{0.5, 0, 0.5});
    model.component("comp1").physics("mbd").feature("hgj2").set("AxisOfJointType", "SelectFromCS");
    model.component("comp1").physics("mbd").feature("hgj2").set("AxisToUse", 2);
    model.component("comp1").physics("mbd").feature("hgj2").create("ct1", "Constraints", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("ct1").set("th_max", "pi/4");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.01,1.5)");
    model.study("std2").label("\u7814\u7a76\uff1a\u7ea6\u675f");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u5bf9\u8f6c\u52a8\uff1a\u7ea6\u675f");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u5173\u8282\u529b\u77e9\uff1a\u7ea6\u675f");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj2.Mx"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u5173\u8282\u529b\u77e9\uff0cx \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj2.Mx", "mbd.hgj2.My"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\u77e9\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\u77e9\uff0cy \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj2.Mx", "mbd.hgj2.My", "mbd.hgj2.Mz"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u5173\u8282\u529b\u77e9\uff0cx \u5206\u91cf", "\u5173\u8282\u529b\u77e9\uff0cy \u5206\u91cf", "\u5173\u8282\u529b\u77e9\uff0cz \u5206\u91cf"});
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").set("ylabel", "\u5173\u8282\u529b\u77e9(N-m)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u80fd\u91cf\uff1a\u7ea6\u675f");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u52bf\u80fd"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot", "mbd.Ws_tot"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd", "\u603b\u5f39\u6027\u5e94\u53d8\u80fd"});
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg7").run();

    model.component("comp1").physics("mbd").feature("hgj2").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("hgj2").feature("ja1").selection().set(33);
    model.component("comp1").physics("mbd").feature("hgj2").set("CenterOfJointType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("hgj2").set("EntityLevel", "Edge");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cje1").selection().set(34, 35, 46, 48);
    model.component("comp1").physics("mbd").feature("hgj2").create("lk1", "Locking", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("lk1").set("th_max", "pi/4");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std3").feature("time").set("tlist", "range(0,0.005,1.5)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"mbd/hgj1/pm1", "mbd/hgj2/ct1"});
    model.study("std3").label("\u7814\u7a76\uff1a\u9501\u5b9a");
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "auto");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg8", "pg3");
    model.result("pg8").run();
    model.result("pg8").label("\u76f8\u5bf9\u8f6c\u52a8\uff1a\u9501\u5b9a");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").run();
    model.result("pg6").run();
    model.result().duplicate("pg9", "pg6");
    model.result("pg9").run();
    model.result("pg9").label("\u5173\u8282\u529b\u77e9\uff1a\u9501\u5b9a");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").run();

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").selection().set(1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").create("hgj3", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj3").set("CenterOfJointType", "UserDefined");
    model.component("comp1").physics("mbd").feature("hgj3").set("xc", new double[]{0.5, 0, 0.5});
    model.component("comp1").physics("mbd").feature("hgj3").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj3").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("sd1").set("k_th", "5e6");
    model.component("comp1").physics("mbd").feature("hgj3").feature("sd1").set("c_th", "1e6");
    model.component("comp1").physics("mbd").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("mbd").feature("ge1").label("\u80fd\u91cf\uff1a\u963b\u5c3c\u5668");
    model.component("comp1").physics("mbd").feature("ge1").setIndex("name", "Wd", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("equation", "d(Wd,t)-mbd.hgj3.Qdamper", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1")
         .setIndex("description", "\u963b\u5c3c\u5668\u4e2d\u7684\u80fd\u91cf\u8017\u6563", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1").set("DependentVariableQuantity", "energy");
    model.component("comp1").physics("mbd").feature("ge1").set("SourceTermQuantity", "power");

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std4").feature("time").set("tlist", "range(0,0.02,5)");
    model.study("std4").feature("time").set("useadvanceddisable", true);
    model.study("std4").feature("time")
         .set("disabledphysics", new String[]{"mbd/hgj1", "mbd/att1", "mbd/att2", "mbd/att3", "mbd/hgj2"});
    model.study("std4").label("\u7814\u7a76\uff1a\u5f39\u7c27 - \u963b\u5c3c\u5668");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg10", "pg3");
    model.result("pg10").run();
    model.result("pg9").run();
    model.result("pg9").label("\u76f8\u5bf9\u8f6c\u52a8\uff1a\u5f39\u7c27 - \u963b\u5c3c\u5668");
    model.result("pg9").set("data", "none");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("expr", new String[]{"comp1.mbd.hgj3.th"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u65cb\u8f6c"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u76f8\u5bf9\u8f6c\u52a8", 0);
    model.result("pg9").run();
    model.result("pg9").set("data", "dset4");
    model.result("pg9").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u80fd\u91cf\uff1a\u5f39\u7c27 - \u963b\u5c3c\u5668");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"Wp"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u52bf\u80fd"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg11").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd"});
    model.result("pg11").feature("glob1").setIndex("expr", "mbd.hgj3.Wspring", 2);
    model.result("pg11").feature("glob1").setIndex("descr", "\u5f39\u7c27\u50a8\u5b58\u7684\u80fd\u91cf", 2);
    model.result("pg11").feature("glob1").setIndex("expr", "Wd", 3);
    model.result("pg11").feature("glob1").set("titletype", "none");
    model.result("pg11").feature("glob1").set("linewidth", 2);
    model.result("pg11").feature("glob1").set("linemarker", "cycle");
    model.result("pg11").feature("glob1").set("markerpos", "interp");
    model.result("pg11").run();
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg11").run();
    model.result("pg11").set("axislimits", true);
    model.result("pg11").set("ymax", "1e6");
    model.result("pg11").run();

    model.component("comp1").physics("mbd").feature("hgj3").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj3").feature("pm1").set("omegap", "t");
    model.component("comp1").physics("mbd").feature("hgj3").feature("pm1")
         .set("ActivationConditionRotational", "conditionallyActive");
    model.component("comp1").physics("mbd").feature("hgj3").feature("pm1").set("i_thp", "t>1");

    model.study().create("std5");
    model.study("std5").create("time", "Transient");
    model.study("std5").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std5").feature("time").set("tlist", "range(0,0.02,2)");
    model.study("std5").feature("time").set("useadvanceddisable", true);
    model.study("std5").feature("time")
         .set("disabledphysics", new String[]{"mbd/gacc1", "mbd/att1", "mbd/hgj1", "mbd/att2", "mbd/att3", "mbd/hgj2", "mbd/hgj3/sd1", "mbd/ge1"});
    model.study("std5").label("\u7814\u7a76\uff1a\u6307\u5b9a\u8fd0\u52a8");
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg9").run();
    model.result().duplicate("pg12", "pg9");
    model.result("pg12").run();
    model.result("pg12").label("\u76f8\u5bf9\u8f6c\u52a8\uff1a\u6307\u5b9a\u8fd0\u52a8");
    model.result("pg12").set("data", "dset5");
    model.result("pg12").run();
    model.result("pg9").run();
    model.result().duplicate("pg13", "pg9");
    model.result("pg13").run();
    model.result("pg13").label("\u76f8\u5bf9\u901f\u5ea6\uff1a\u6307\u5b9a\u8fd0\u52a8");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").run();
    model.result("pg13").feature("glob1").set("expr", new String[]{"mbd.hgj3.th_t"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg13").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg13").feature("glob1").setIndex("unit", "deg/s", 0);
    model.result("pg13").run();
    model.result("pg13").run();

    model.component("comp1").physics("mbd").feature("hgj3")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("hgj3").create("fric1", "Friction", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("fric1").set("mu", 0.6);
    model.component("comp1").physics("mbd").feature("hgj3").feature("fric1").set("rh", 0.3);
    model.component("comp1").physics("mbd").feature().duplicate("ge2", "ge1");
    model.component("comp1").physics("mbd").feature("ge2").label("\u80fd\u91cf\uff1a\u6469\u64e6");
    model.component("comp1").physics("mbd").feature("ge2").setIndex("name", "Wf", 0, 0);
    model.component("comp1").physics("mbd").feature("ge2").setIndex("equation", "d(Wf,t)-mbd.hgj3.Qfriction", 0, 0);
    model.component("comp1").physics("mbd").feature("ge2")
         .setIndex("description", "\u7531\u6469\u64e6\u4ea7\u751f\u7684\u80fd\u91cf\u635f\u5931", 0, 0);

    model.study().create("std6");
    model.study("std6").create("time", "Transient");
    model.study("std6").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std6").feature("time").set("tlist", "range(0,0.05,10)");
    model.study("std6").feature("time").set("useadvanceddisable", true);
    model.study("std6").feature("time")
         .set("disabledphysics", new String[]{"mbd/att1", "mbd/hgj1", "mbd/att2", "mbd/att3", "mbd/hgj2", "mbd/hgj3/sd1", "mbd/hgj3/pm1", "mbd/ge1"});
    model.study("std6").label("\u7814\u7a76\uff1a\u6469\u64e6");
    model.study("std6").setGenPlots(false);
    model.study("std6").showAutoSequences("all");

    model.sol("sol6").feature("v1").feature("comp1_ODE2").set("scalemethod", "manual");
    model.sol("sol6").feature("v1").feature("comp1_ODE2").set("scaleval", "1e6");

    model.study("std6").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result("pg9").run();
    model.result().duplicate("pg14", "pg9");
    model.result("pg14").run();
    model.result("pg14").label("\u76f8\u5bf9\u8f6c\u52a8\uff1a\u6469\u64e6");
    model.result("pg14").set("data", "dset6");
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u6469\u64e6\u529b\u77e9");
    model.result("pg15").run();
    model.result("pg15").feature("glob1").setIndex("expr", "mbd.hgj3.fric1.Mf", 0);
    model.result("pg15").run();
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u80fd\u91cf\uff1a\u6469\u64e6");
    model.result("pg16").set("data", "dset6");
    model.result("pg16").setIndex("looplevelinput", "interp", 0);
    model.result("pg16").setIndex("interp", "range(0,0.2,10)", 0);
    model.result("pg16").create("glob1", "Global");
    model.result("pg16").feature("glob1").set("markerpos", "datapoints");
    model.result("pg16").feature("glob1").set("linewidth", "preference");
    model.result("pg16").feature("glob1").set("expr", new String[]{"Wp"});
    model.result("pg16").feature("glob1").set("descr", new String[]{"\u52bf\u80fd"});
    model.result("pg16").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg16").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot"});
    model.result("pg16").feature("glob1").set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd"});
    model.result("pg16").feature("glob1").setIndex("expr", "Wf", 2);
    model.result("pg16").feature("glob1").set("titletype", "none");
    model.result("pg16").feature("glob1").set("linewidth", 2);
    model.result("pg16").feature("glob1").set("linemarker", "cycle");
    model.result("pg16").feature("glob1").set("markerpos", "interp");
    model.result("pg16").run();
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg16").run();
    model.result("pg16").set("axislimits", true);
    model.result("pg16").set("ymax", "3e6");
    model.result("pg16").run();
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

    model.study("std5").feature("time")
         .set("disabledphysics", new String[]{"mbd/gacc1", "mbd/att1", "mbd/hgj1", "mbd/att2", "mbd/att3", "mbd/hgj2", "mbd/hgj3/sd1", "mbd/ge1", "mbd/hgj3/fric1", "mbd/ge2"});
    model.study("std4").feature("time")
         .set("disabledphysics", new String[]{"mbd/hgj1", "mbd/att1", "mbd/att2", "mbd/att3", "mbd/hgj2", "mbd/hgj3/pm1", "mbd/hgj3/fric1", "mbd/ge2"});
    model.study("std3").feature("time")
         .set("disabledphysics", new String[]{"mbd/hgj1/pm1", "mbd/hgj2/ct1", "mbd/rd1", "mbd/rd2", "mbd/hgj3", "mbd/ge1", "mbd/ge2"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time")
         .set("disabledphysics", new String[]{"mbd/hgj2/lk1", "mbd/rd1", "mbd/rd2", "mbd/hgj3", "mbd/ge1", "mbd/ge2"});
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time")
         .set("disabledphysics", new String[]{"mbd/hgj1/pm1", "mbd/hgj2/ct1", "mbd/hgj2/lk1", "mbd/rd1", "mbd/rd2", "mbd/hgj3", "mbd/ge1", "mbd/ge2"});

    model.result("pg1").run();

    model.title("\u53cc\u6446\u7684\u52a8\u529b\u5b66\u5206\u6790");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u53cc\u6446\u7684\u591a\u4f53\u52a8\u529b\u5b66\u4eff\u771f\u3002\u5728\u4e0d\u540c\u7684\u5de5\u51b5\u4e0b\uff0c\u5c06\u4e24\u4e2a\u81c2\u90fd\u4f5c\u4e3a\u67d4\u6027\u5355\u5143\u548c\u521a\u6027\u5355\u5143\u8fdb\u884c\u5efa\u6a21\u3002\u5176\u4e2d\u6f14\u793a\u4e86\u5173\u8282\u7684\u5404\u79cd\u7279\u5f81\uff0c\u4f8b\u5982\u505c\u6b62\u6761\u4ef6\u3001\u9501\u5b9a\u6761\u4ef6\u3001\u5f39\u7c27-\u963b\u5c3c\u5668\u548c\u6307\u5b9a\u8fd0\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("double_pendulum.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
