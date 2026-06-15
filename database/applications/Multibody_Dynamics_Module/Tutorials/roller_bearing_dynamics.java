/*
 * roller_bearing_dynamics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:25 by COMSOL 6.3.0.290. */
public class roller_bearing_dynamics {

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
    model.param().set("d_out", "100[mm]", "\u5916\u5f84");
    model.param().set("db", "56[mm]", "\u7f38\u5f84");
    model.param().set("cl", "0.125[mm]", "\u95f4\u9699");
    model.param().set("th", "5[mm]-cl", "\u539a\u5ea6");
    model.param().set("ro", "d_out/2-th", "\u5916\u5708\uff0c\u5185\u534a\u5f84");
    model.param().set("ri", "db/2+th", "\u5185\u5708\uff0c\u5916\u534a\u5f84");
    model.param().set("rr", "6[mm]", "\u6eda\u5b50\u534a\u5f84");
    model.param().set("fp", "1/10000", "\u7f5a\u56e0\u5b50\u4e58\u5b50");
    model.param().set("mu", "0.1", "\u6469\u64e6\u7cfb\u6570");
    model.param().set("load", "100[N]", "\u8f74\u627f\u8d1f\u8f7d");
    model.param().set("rpm", "5000", "\u5185\u5708\u6bcf\u5206\u949f\u8f6c\u6570");
    model.param().set("omega", "2*pi[rad]*rpm/60[s]", "\u5185\u5708\u89d2\u901f\u5ea6");
    model.param().set("T", "2*pi/omega", "\u5185\u5708\u65f6\u95f4\u5468\u671f");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "roller_bearing_dynamics.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u4fdd\u6301\u67b6");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("imp1(2)", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5708");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1(1)", 1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("imp1(15)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("sel2").set("color", "custom");
    model.component("comp1").geom("geom1").feature("sel2")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6eda\u5b50");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").feature("comsel1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("comsel1")
         .set("customcolor", new double[]{0.4431372582912445, 0.18039216101169586, 0});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u4fdd\u6301\u67b6\u548c\u6eda\u5b50");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "comsel1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .label("\u4fdd\u6301\u67b6\u8fb9\u754c\u548c\u6eda\u5b50\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5916\u5708");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("imp1(15)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u5916\u5708\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel3"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("interior", true);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("comsel2", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel2").label("\u6ca1\u6709\u5916\u5708\u7684\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("comsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel2").set("input", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").run("comsel2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 1);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Fnx", "try_catch(mbd.Fnx_rbc13,0)+try_catch(mbd.Fnx_rbc14,0)+try_catch(mbd.Fnx_rbc15,0)+try_catch(mbd.Fnx_rbc16,0)+try_catch(mbd.Fnx_rbc17,0)+try_catch(mbd.Fnx_rbc18,0)+try_catch(mbd.Fnx_rbc19,0)+try_catch(mbd.Fnx_rbc20,0)+try_catch(mbd.Fnx_rbc21,0)+try_catch(mbd.Fnx_rbc22,0)+try_catch(mbd.Fnx_rbc23,0)+try_catch(mbd.Fnx_rbc24,0)", "\u63a5\u89e6\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Fny", "try_catch(mbd.Fny_rbc13,0)+try_catch(mbd.Fny_rbc14,0)+try_catch(mbd.Fny_rbc15,0)+try_catch(mbd.Fny_rbc16,0)+try_catch(mbd.Fny_rbc17,0)+try_catch(mbd.Fny_rbc18,0)+try_catch(mbd.Fny_rbc19,0)+try_catch(mbd.Fny_rbc20,0)+try_catch(mbd.Fny_rbc21,0)+try_catch(mbd.Fny_rbc22,0)+try_catch(mbd.Fny_rbc23,0)+try_catch(mbd.Fny_rbc24,0)", "\u63a5\u89e6\u529b\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Fnz", "try_catch(mbd.Fnz_rbc13,0)+try_catch(mbd.Fnz_rbc14,0)+try_catch(mbd.Fnz_rbc15,0)+try_catch(mbd.Fnz_rbc16,0)+try_catch(mbd.Fnz_rbc17,0)+try_catch(mbd.Fnz_rbc18,0)+try_catch(mbd.Fnz_rbc19,0)+try_catch(mbd.Fnz_rbc20,0)+try_catch(mbd.Fnz_rbc21,0)+try_catch(mbd.Fnz_rbc22,0)+try_catch(mbd.Fnz_rbc23,0)+try_catch(mbd.Fnz_rbc24,0)", "\u63a5\u89e6\u529b\uff0cz \u5206\u91cf");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", "0[s]");
    model.component("comp1").func("rm1").set("slope", 1000);
    model.component("comp1").func("rm1").set("cutoffactive", true);

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

    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createRigidDomains");
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599 1\uff1a\u5916\u5708");
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").feature("rd3")
         .label("\u521a\u6027\u6750\u6599 3\uff1a\u4fdd\u6301\u67b6");
    model.component("comp1").physics("mbd").feature("rd3").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd3").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd3").feature("pdr1")
         .set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd3").feature("pdr1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("mbd").feature("rd3").feature("pdr1").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("mbd").feature("rd6").label("\u521a\u6027\u6750\u6599 6\uff1a\u5185\u5708");
    model.component("comp1").physics("mbd").feature("rd6").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd6").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rd6").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd6").feature("pdr1").set("Omega", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("rd6").feature("pdr1").set("phi0", "omega*t");
    model.component("comp1").physics("mbd").feature("rd6").create("af1", "AppliedForce", -1);
    model.component("comp1").physics("mbd").feature("rd6").feature("af1")
         .set("Ft", new String[]{"0", "0", "-load*rm1(t)"});
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createJoints");
    model.component("comp1").physics("mbd").create("rbc1", "RigidBodyContact", -1);
    model.component("comp1").physics("mbd").feature("rbc1").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("rbc1").set("ShapeParaSource", "UserDefined");
    model.component("comp1").physics("mbd").feature("rbc1").set("rs", "rr");
    model.component("comp1").physics("mbd").feature("rbc1").set("SourcePoint", "CentroidOfSource");
    model.component("comp1").physics("mbd").feature("rbc1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("rbc1").set("ShapeParaDest", "UserDefined");
    model.component("comp1").physics("mbd").feature("rbc1").set("rd", "ro");
    model.component("comp1").physics("mbd").feature("rbc1").set("DestinationPoint", "CentroidOfDestination");
    model.component("comp1").physics("mbd").feature("rbc1").set("DestInside", true);
    model.component("comp1").physics("mbd").feature("rbc1").set("fp", "fp");
    model.component("comp1").physics("mbd").feature("rbc1").set("taun", "1[ms]*10");
    model.component("comp1").physics("mbd").feature("rbc1").create("fric1", "Friction", -1);
    model.component("comp1").physics("mbd").feature("rbc1").feature("fric1").set("mu", "mu");
    model.component("comp1").physics("mbd").feature("rbc1").feature("fric1").set("v0", "mbd.diag*1e-3[1/s]*10");
    model.component("comp1").physics("mbd").feature().duplicate("rbc2", "rbc1");
    model.component("comp1").physics("mbd").feature("rbc2").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature().duplicate("rbc3", "rbc2");
    model.component("comp1").physics("mbd").feature("rbc3").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature().duplicate("rbc4", "rbc3");
    model.component("comp1").physics("mbd").feature("rbc4").set("Source", "rd7");
    model.component("comp1").physics("mbd").feature().duplicate("rbc5", "rbc4");
    model.component("comp1").physics("mbd").feature("rbc5").set("Source", "rd8");
    model.component("comp1").physics("mbd").feature().duplicate("rbc6", "rbc5");
    model.component("comp1").physics("mbd").feature("rbc6").set("Source", "rd9");
    model.component("comp1").physics("mbd").feature().duplicate("rbc7", "rbc6");
    model.component("comp1").physics("mbd").feature("rbc7").set("Source", "rd10");
    model.component("comp1").physics("mbd").feature().duplicate("rbc8", "rbc7");
    model.component("comp1").physics("mbd").feature("rbc8").set("Source", "rd11");
    model.component("comp1").physics("mbd").feature().duplicate("rbc9", "rbc8");
    model.component("comp1").physics("mbd").feature("rbc9").set("Source", "rd12");
    model.component("comp1").physics("mbd").feature().duplicate("rbc10", "rbc9");
    model.component("comp1").physics("mbd").feature("rbc10").set("Source", "rd13");
    model.component("comp1").physics("mbd").feature().duplicate("rbc11", "rbc10");
    model.component("comp1").physics("mbd").feature("rbc11").set("Source", "rd14");
    model.component("comp1").physics("mbd").feature().duplicate("rbc12", "rbc11");
    model.component("comp1").physics("mbd").feature("rbc12").set("Source", "rd15");

    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("rbc1");
    model.nodeGroup("grp3").add("rbc2");
    model.nodeGroup("grp3").add("rbc3");
    model.nodeGroup("grp3").add("rbc4");
    model.nodeGroup("grp3").add("rbc5");
    model.nodeGroup("grp3").add("rbc6");
    model.nodeGroup("grp3").add("rbc7");
    model.nodeGroup("grp3").add("rbc8");
    model.nodeGroup("grp3").add("rbc9");
    model.nodeGroup("grp3").add("rbc10");
    model.nodeGroup("grp3").add("rbc11");
    model.nodeGroup("grp3").add("rbc12");
    model.nodeGroup("grp3").label("\u6eda\u5b50-\u5916\u5708\u63a5\u89e6");
    model.nodeGroup().duplicate("grp4", "grp3");
    model.nodeGroup("grp4").label("\u6eda\u5b50-\u5185\u5708\u63a5\u89e6");

    model.component("comp1").physics("mbd").feature("rbc13").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc13").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc13").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc14").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc14").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc14").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc15").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc15").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc15").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc16").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc16").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc16").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc17").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc17").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc17").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc18").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc18").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc18").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc19").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc19").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc19").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc20").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc20").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc20").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc21").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc21").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc21").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc22").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc22").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc22").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc23").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc23").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc23").set("DestInside", false);
    model.component("comp1").physics("mbd").feature("rbc24").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("rbc24").set("rd", "ri");
    model.component("comp1").physics("mbd").feature("rbc24").set("DestInside", false);
    model.component("comp1").physics("mbd").prop("Results").set("ReferenceFrame", "rd3");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T/500,5*T)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 15);
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");

    return model;
  }

  public static Model run2(Model model) {
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

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").set("inherithide", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_comsel2");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().named("geom1_adjsel2");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u63a5\u89e6\u529b\u548c\u901f\u5ea6");
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg2").feature().remove("arwl1");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("coloring", "uniform");
    model.result("pg2").feature("vol1").set("color", "gray");
    model.result("pg2").feature("vol1").set("titletype", "none");
    model.result("pg2").feature("vol1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("geom1_sel2");
    model.result("pg2").feature("vol1").feature("sel1").selection().set(1, 4, 9);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mbd.vel");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_adjsel1");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"Fnx", "Fny", "Fnz"});
    model.result("pg2").feature("arws1").set("descractive", true);
    model.result("pg2").feature("arws1").set("descr", "\u63a5\u89e6\u529b (N)");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "5e-4");
    model.result("pg2").feature("arws1").set("color", "green");
    model.result("pg2").feature("arws1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().named("geom1_adjsel1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6 [\u4fdd\u6301\u67b6]");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.vel_ref");
    model.result("pg3").run();
    model.result("pg3").feature().remove("arws1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u89d2\u901f\u5ea6 [\u5185\u5708\u548c\u4fdd\u6301\u67b6]");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.rd6.th_ty", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.rd3.th_ty", 1);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u5185\u5708", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u4fdd\u6301\u67b6", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u89d2\u901f\u5ea6 [\u6eda\u5b50]");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd11.th_ty", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd15.th_ty", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.rd10.th_ty", 2);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("legends", "A", 0);
    model.result("pg5").feature("glob1").setIndex("legends", "B", 1);
    model.result("pg5").feature("glob1").setIndex("legends", "C", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u63a5\u89e6\u548c\u6469\u64e6\u529b [\u6eda\u5b50-\u5185\u5708]");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{});
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.rbc20.Fn", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.rbc20.Ff", 1);
    model.result("pg6").feature("glob1").set("legendmethod", "automatic");
    model.result("pg6").run();
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u529b (N)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6469\u64e6\u80fd\u8017\u7387 [\u6eda\u5b50-\u5185\u5708]");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc20.Qf", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc24.Qf", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "mbd.rbc19.Qf", 2);
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "A", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "B", 1);
    model.result("pg7").feature("glob1").setIndex("legends", "C", 2);
    model.result("pg7").run();
    model.result("pg7").set("ylabelactive", false);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u5185\u5708\u8f68\u9053");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").setIndex("expr", "mbd.rd6.w", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "mm", 0);
    model.result("pg8").feature("glob1").set("legend", false);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "mbd.rd6.u");
    model.result("pg8").feature("glob1").set("xdataunit", "mm");
    model.result("pg8").run();
    model.result("pg8").run();
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
    model.result().export("anim1").label("\u4f4d\u79fb (mbd)");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u63a5\u89e6\u529b\u548c\u901f\u5ea6");
    model.result().export("anim2").set("plotgroup", "pg2");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u901f\u5ea6 [\u4fdd\u6301\u67b6]");
    model.result().export("anim3").set("plotgroup", "pg3");
    model.result("pg2").run();

    model.title("\u5706\u67f1\u6eda\u5b50\u8f74\u627f\u7684\u52a8\u529b\u5b66");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u5706\u67f1\u6eda\u5b50\u8f74\u627f\u7cfb\u7edf\u7684\u52a8\u529b\u5b66\u3002\u8be5\u7cfb\u7edf\u7531\u63d2\u5728\u5185\u5708\u548c\u5916\u5708\u4e4b\u95f4\u7684\u5706\u67f1\u6eda\u5b50\u5143\u4ef6\u7ec4\u6210\uff0c\u5e76\u7531\u4fdd\u6301\u67b6\u56fa\u5b9a\u5728\u9002\u5f53\u7684\u4f4d\u7f6e\u3002\u5176\u4e2d\u5047\u8bbe\u7cfb\u7edf\u7684\u6240\u6709\u90e8\u4ef6\u90fd\u662f\u521a\u6027\u7684\uff0c\u5e76\u5047\u8bbe\u5185\u5708\u4e0e\u4ee5\u6307\u5b9a\u901f\u5ea6\u65cb\u8f6c\u7684\u8f74\u8fde\u63a5\uff0c\u5916\u5708\u5219\u4e0e\u56fa\u5b9a\u57fa\u7840\u8fde\u63a5\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u5e26\u6469\u64e6\u7684\u521a\u4f53\u63a5\u89e6\u6a21\u62df\u6eda\u5b50\u4e0e\u6eda\u9053\u4e4b\u95f4\u7684\u6469\u64e6\u63a5\u89e6\u3002\u6eda\u5b50\u4e0e\u4fdd\u6301\u67b6\u4e4b\u95f4\u7684\u8fde\u63a5\u901a\u8fc7\u94f0\u94fe\u5173\u8282\u8fdb\u884c\u4e86\u7b80\u5316\u3002\u5176\u4e2d\u5bf9\u5185\u5708\u65bd\u52a0\u5916\u90e8\u8f7d\u8377\uff0c\u5e76\u6267\u884c\u77ac\u6001\u7814\u7a76\uff0c\u4ee5\u5206\u6790\u5185\u5708\u7684\u6a2a\u5411\u52a8\u529b\u5b66\u3001\u6eda\u5b50\u548c\u4fdd\u6301\u67b6\u7684\u52a8\u529b\u5b66\u3001\u63a5\u89e6\u529b\u548c\u6469\u64e6\u529b\u4ee5\u53ca\u6469\u64e6\u5f15\u8d77\u7684\u80fd\u8017\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("roller_bearing_dynamics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
