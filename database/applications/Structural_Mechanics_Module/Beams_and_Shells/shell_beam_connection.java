/*
 * shell_beam_connection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:01 by COMSOL 6.3.0.290. */
public class shell_beam_connection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lp", "2[m]", "\u677f\u957f");
    model.param().set("wp", "0.4[m]", "\u677f\u5bbd");
    model.param().set("tp", "10[mm]", "\u677f\u539a");
    model.param().set("hbl", "40[mm]", "\u7eb5\u5411\u6881\u9ad8\u5ea6");
    model.param().set("wbl", "30[mm]", "\u7eb5\u5411\u6881\u5bbd\u5ea6");
    model.param().set("hbh", "70[mm]", "\u5de5\u5b57\u6881\u9ad8\u5ea6");
    model.param().set("wbh", "60[mm]", "\u5de5\u5b57\u6881\u5bbd\u5ea6");
    model.param().set("tbh", "8[mm]", "\u5de5\u5b57\u6881\u539a\u5ea6");
    model.param().set("lbh", "0.8[m]", "\u5de5\u5b57\u6881\u957f\u5ea6");
    model.param().set("wbc", "50[mm]", "\u4e2d\u5fc3\u6881\u5bbd\u5ea6");
    model.param().set("lbc", "1.2[m]", "\u4e2d\u5fc3\u6881\u957f\u5ea6");
    model.param().set("Fh", "500[N]", "\u5de5\u5b57\u6881\u7aef\u90e8\u7684\u529b");
    model.param().set("Fc", "50[N]", "\u4e2d\u5fc3\u6881\u7aef\u90e8\u7684\u529b");
    model.param().set("p", "5[kPa]", "\u677f\u4e0a\u538b\u529b");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"lp", "wp", "tp"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"lp", "wbl", "hbl"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-hbl"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "wp-wbl", "-hbl"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"lbc", "wbc", "tp"});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"lp", "(wp-wbc)/2", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "tp");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"hbh-2*tbh", "tbh"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"lp/2", "wp/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"tbh", "wbh"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"lp/2-hbh/2+tbh/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "wp/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"lp/2+hbh/2-tbh/2", "wp/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "lbh", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input")
         .set("blk1", "blk2", "blk3", "blk4", "ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "wp*1.5");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new String[]{"lp", "wp"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "lp", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "wp/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "lp+lbc", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "wp/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "wp-wbl/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-(tp+hbl)/2", 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "lp", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "wp-wbl/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-(tp+hbl)/2", 1, 2);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "lp/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "wp/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "lp/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "wp/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "lbh+tp/2", 1, 2);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u58f3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("wp2", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("H \u6881");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("pol3", 1);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u6881");
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u52a0\u5f3a\u7b4b");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("pol2", 1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("wp2", 1);
    model.component("comp1").geom("geom1").feature("sel3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u7a81\u51fa\u7684\u6881");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("pol1", 1);
    model.component("comp1").geom("geom1").feature("sel4").set("contributeto", "csel1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").label("Norton");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk2").selection().named("geom1_sel1");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk3").selection().named("geom1_csel1_edg");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2, 5, 11);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(20, 26, 32);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"Fh", "0", "0"});
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(46);
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl2").set("force", new String[]{"0", "0", "-Fc"});
    model.component("comp1").physics("solid").create("bndl3", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl3").selection().set(8);
    model.component("comp1").physics("solid").feature("bndl3")
         .set("forceReferenceArea", new String[]{"0", "0", "-p"});
    model.component("comp1").physics("shell").selection().set();
    model.component("comp1").physics("shell").selection().named("geom1_sel1");
    model.component("comp1").physics("shell").feature("to1").set("d", "tp");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("shell").feature("fix1").selection().set(1);
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().named("geom1_sel1");
    model.component("comp1").physics("shell").feature("fl1").set("forceReferenceArea", new String[]{"0", "0", "-p"});
    model.component("comp1").physics("beam").selection().set();
    model.component("comp1").physics("beam").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_rect", "hbl");
    model.component("comp1").physics("beam").feature("csd1").set("hz_rect", "wbl");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").create("csd2", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd2").selection().named("geom1_sel2");
    model.component("comp1").physics("beam").feature("csd2").set("SectionType", "H_Profile");
    model.component("comp1").physics("beam").feature("csd2").set("hy_H", "hbh");
    model.component("comp1").physics("beam").feature("csd2").set("hz_H", "wbh");
    model.component("comp1").physics("beam").feature("csd2").set("ty_H", "tbh");
    model.component("comp1").physics("beam").feature("csd2").set("tz_H", "tbh");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("vector_beam", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").create("csd3", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd3").selection().named("geom1_sel4");
    model.component("comp1").physics("beam").feature("csd3").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd3").set("hy_rect", "tp");
    model.component("comp1").physics("beam").feature("csd3").set("hz_rect", "wbc");
    model.component("comp1").physics("beam").feature("csd3").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd3").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl1").selection().set(27);
    model.component("comp1").physics("beam").feature("pl1").set("forcePoint", new String[]{"Fh", "0", "0"});
    model.component("comp1").physics("beam").create("pl2", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl2").selection().set(58);
    model.component("comp1").physics("beam").feature("pl2").set("forcePoint", new String[]{"0", "0", "-Fc"});

    model.component("comp1").multiphysics().create("shbc1", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc1").set("connectionSettings", "BeamSharedEdge");
    model.component("comp1").multiphysics("shbc1").set("selectionControl", true);
    model.component("comp1").multiphysics("shbc1").selection("sharedEdgShellSelection").set(2);
    model.component("comp1").multiphysics("shbc1").set("offset", "OffsetVect");
    model.component("comp1").multiphysics("shbc1").set("d0", new String[]{"0", "wbl/2", "-(tp+hbl)/2"});
    model.component("comp1").multiphysics().create("shbc2", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc2").set("connectionSettings", "BeamParallelEdge");
    model.component("comp1").multiphysics("shbc2").selection("paraEdgShellSelection").set(4);
    model.component("comp1").multiphysics("shbc2").selection("paraEdgBeamSelection").set(3);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").multiphysics().create("shbc3", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc3").set("connectionSettings", "BeamPointShellBoundary");
    model.component("comp1").multiphysics("shbc3").set("selectionControl", true);
    model.component("comp1").multiphysics("shbc3").selection("bndPointShellSelection").set(1);
    model.component("comp1").multiphysics("shbc3").selection("bndPointBeamSelection").set(26);
    model.component("comp1").multiphysics("shbc3").set("connectedRegionBndr", "ConCriterion");
    model.component("comp1").multiphysics("shbc3").set("i_conn", "(abs(x-lp/2)<hbh/2)*(abs(y-wp/2)<wbh/2)");
    model.component("comp1").multiphysics().create("shbc4", "ShellBeamConnection", -1);
    model.component("comp1").multiphysics("shbc4").set("selectionControl", true);
    model.component("comp1").multiphysics("shbc4").selection("edgPointShellSelection").set(68, 69);
    model.component("comp1").multiphysics("shbc4").selection("edgPointBeamSelection").set(41);
    model.component("comp1").multiphysics("shbc4").set("connectedRegion", "DistManual");
    model.component("comp1").multiphysics("shbc4").set("rc", "wbc/2");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2, 3, 68, 69);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(68, 69);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "wbc/2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(26);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "hbh/10");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 6, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 6, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1shellshl");
    model.result("pg2").label("\u5e94\u529b (shell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1beam");
    model.result("pg3").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("surf1", "pg2/surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("inheritplot", "vol1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "dset1beam");
    model.result("pg1").run();
    model.result("pg1").feature().copy("surf2", "pg3/surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff08\u5b9e\u4f53\u3001\u58f3\u548c\u6881\uff09");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises \u5e94\u529b (N/m<sup>2</sup>)");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\u6bd4\u8f83");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(7);
    model.result("pg4").feature("lngr1").set("expr", "solid.mises");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u56fa\u4f53", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").selection().set(3);
    model.result("pg4").feature("lngr2").set("expr", "beam.mises");
    model.result("pg4").feature("lngr2").set("titletype", "none");
    model.result("pg4").feature("lngr2").set("resolution", "norefine");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u4e2d\u5fc3\u7ebf\u5904\u7684\u6881", 0);
    model.result("pg4").feature().duplicate("lngr3", "lngr2");
    model.result("pg4").run();
    model.result("pg4").feature("lngr3").selection().set(2);
    model.result("pg4").feature("lngr3").setIndex("legends", "\u58f3\u8fb9\u4e0a\u7684\u6881", 0);
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u58f3\u548c\u6881\u8fde\u63a5");

    model
         .description("\u5f88\u591a\u5de5\u7a0b\u7ed3\u6784\u7531\u8584\u4e14\u7ec6\u957f\u7684\u7ec4\u4ef6\u6784\u6210\uff0c\u5982\u679c\u4f7f\u7528\u5168\u56fa\u4f53\u6a21\u578b\u5c06\u4ea7\u751f\u76f8\u5f53\u591a\u7684\u5c0f\u5355\u5143\u3002\u5bf9\u4e8e\u8fd9\u6837\u7684\u7ed3\u6784\uff0c\u4f7f\u7528\u58f3\u6216\u6881\u5355\u5143\u4f1a\u5927\u5927\u63d0\u9ad8\u6548\u7387\u3002\n\n\u5728\u8fd9\u4e2a\u6559\u7a0b\u548c\u9a8c\u8bc1\u95ee\u9898\u4e2d\uff0c\u60a8\u5c06\u5b66\u4e60\u5982\u4f55\u5728\u4e0d\u540c\u7684\u60c5\u51b5\u4e0b\u8fde\u63a5\u58f3\u548c\u6881\u5355\u5143\u3002\u4eff\u771f\u7ed3\u679c\u8fd8\u4e0e\u540c\u4e00\u51e0\u4f55\u7684\u56fa\u4f53\u6a21\u578b\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("shell_beam_connection.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
