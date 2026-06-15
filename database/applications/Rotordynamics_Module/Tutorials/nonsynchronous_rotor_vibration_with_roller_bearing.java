/*
 * nonsynchronous_rotor_vibration_with_roller_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:47 by COMSOL 6.3.0.290. */
public class nonsynchronous_rotor_vibration_with_roller_bearing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/rotbm", true);

    model.param().label("\u53c2\u6570\uff1a\u8f6c\u5b50");
    model.param().set("Ow", "3000[rpm]");
    model.param().descr("Ow", "\u8f6c\u5b50\u7684\u89d2\u901f\u5ea6");
    model.param().set("d", "0.04[m]");
    model.param().descr("d", "\u8f6c\u5b50\u76f4\u5f84");
    model.param().set("rho_disk", "7850[kg/m^3]");
    model.param().descr("rho_disk", "\u5706\u76d8\u5bc6\u5ea6");
    model.param().set("R_disk", "0.08[m]");
    model.param().descr("R_disk", "\u5706\u76d8\u534a\u5f84");
    model.param().set("h_disk", "0.03[m]");
    model.param().descr("h_disk", "\u5706\u76d8\u539a\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u8f74\u627f");
    model.param("par2").set("C", "1e-4[m]");
    model.param("par2").descr("C", "\u8f74\u627f\u4e2d\u7684\u5f84\u5411\u95f4\u9699");
    model.param("par2").set("f", "0.7");
    model.param("par2").descr("f", "\u586b\u5145\u7387");
    model.param("par2").set("N", "10");
    model.param("par2").descr("N", "\u6eda\u5b50\u6570");
    model.param("par2").set("db", "d/(N/(f*pi)-1)");
    model.param("par2").descr("db", "\u7403\u5f84");
    model.param("par2").set("dp", "d+db");
    model.param("par2").descr("dp", "\u8282\u5706\u76f4\u5f84");
    model.param("par2").set("rin", "0.53*db");
    model.param("par2").descr("rin", "\u5185\u5708\u534a\u5f84");
    model.param("par2").set("rout", "0.53*db");
    model.param("par2").descr("rout", "\u5916\u5708\u534a\u5f84");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 0.5 1");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "Ow");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "d");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(2);
    model.component("comp1").physics("rotbm").feature("disk1").set("COM", "Relative");
    model.component("comp1").physics("rotbm").feature("disk1").set("zr", "1e-4[m]");
    model.component("comp1").physics("rotbm").feature("disk1").set("SpecifiedBy", "GeomDim");
    model.component("comp1").physics("rotbm").feature("disk1").set("rho", "rho_disk");
    model.component("comp1").physics("rotbm").feature("disk1").set("d", "2*R_disk");
    model.component("comp1").physics("rotbm").feature("disk1").set("h", "h_disk");
    model.component("comp1").physics("rotbm").create("rrb1", "RadialRollerBearing", 0);
    model.component("comp1").physics("rotbm").feature("rrb1").selection().set(1);
    model.component("comp1").physics("rotbm").feature("rrb1").set("Nb", "N");
    model.component("comp1").physics("rotbm").feature("rrb1").set("d_ball", "db");
    model.component("comp1").physics("rotbm").feature("rrb1").set("d_pitch", "dp");
    model.component("comp1").physics("rotbm").feature("rrb1").set("r_in", "rin");
    model.component("comp1").physics("rotbm").feature("rrb1").set("r_out", "rout");
    model.component("comp1").physics("rotbm").feature("rrb1").set("c_radial", "C");
    model.component("comp1").physics("rotbm").feature().duplicate("rrb2", "rrb1");
    model.component("comp1").physics("rotbm").feature("rrb2").selection().set(3);
    model.component("comp1").physics("rotbm").create("gr1", "Gravity", 1);

    model.study("std1").create("batsw", "BatchSweep");
    model.study("std1").feature("batsw").setIndex("pname", "C", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "m", 0);
    model.study("std1").feature("batsw").setIndex("pname", "C", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "", 0);
    model.study("std1").feature("batsw").setIndex("punit", "m", 0);
    model.study("std1").feature("batsw").setIndex("plistarr", "2e-5 5e-5 2e-4", 0);
    model.study("std1").feature("batsw").setIndex("punit", "m", 0);
    model.study("std1").feature("batsw").set("clearsol", false);
    model.study("std1").feature("batsw").set("clearmesh", false);
    model.study("std1").feature("batsw").set("maxallow", 3);
    model.study("std1").feature("time").set("tlist", "range(0,2e-4,0.2)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("b1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");
    model.batch("b1").feature("daDef").run();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1001, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"rotbm.mises"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (rotbm)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "rotbm.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").set("expr", new String[]{"1"});
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line2").set("tuberadiusscale", 1);
    model.result("pg1").feature("line2").set("tubeendcaps", false);
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line2").set("threshold", "manual");
    model.result("pg1").feature("line2").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line2").set("titletype", "none");
    model.result("pg1").feature("line2").label("\u8f6c\u5b50");
    model.result("pg1").feature("line2").create("def", "Deform");
    model.result("pg1").feature("line2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line2").feature("def").set("scale", "1");
    model.result("pg1").feature("line2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(2);
    model.result("pg1").feature("pttraj1").selection().inherit(false);
    model.result("pg1").feature("pttraj1").selection().embedded(false);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj1").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj1")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj1")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj1").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj1").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj1").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("plotdata", "points");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection().set(1);
    model.result("pg1").feature("pttraj2").selection().inherit(false);
    model.result("pg1").feature("pttraj2").selection().embedded(false);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj2")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.rrb1.e3gx ", "Y-1.0*rotbm.re*rotbm.rrb1.e3gy ", "Z-1.0*rotbm.re*rotbm.rrb1.e3gz "});
    model.result("pg1").feature("pttraj2")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.rrb1.e3gx ", "rotbm.re*rotbm.rrb1.e3gy ", "rotbm.re*rotbm.rrb1.e3gz "});
    model.result("pg1").feature("pttraj2").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj2").set("arrowbase", "head");
    model.result("pg1").feature("pttraj2").set("arrowscale", "10");
    model.result("pg1").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj2")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").label("\u5f84\u5411\u6eda\u5b50\u8f74\u627f 1");
    model.result("pg1").feature("pttraj2").create("def", "Deform");
    model.result("pg1").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj3", "PointTrajectories");
    model.result("pg1").feature("pttraj3").set("plotdata", "points");
    model.result("pg1").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj3").selection().set(3);
    model.result("pg1").feature("pttraj3").selection().inherit(false);
    model.result("pg1").feature("pttraj3").selection().embedded(false);
    model.result("pg1").feature("pttraj3").set("linetype", "none");
    model.result("pg1").feature("pttraj3").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj3")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.rrb2.e3gx ", "Y-1.0*rotbm.re*rotbm.rrb2.e3gy ", "Z-1.0*rotbm.re*rotbm.rrb2.e3gz "});
    model.result("pg1").feature("pttraj3")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.rrb2.e3gx ", "rotbm.re*rotbm.rrb2.e3gy ", "rotbm.re*rotbm.rrb2.e3gz "});
    model.result("pg1").feature("pttraj3").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj3").set("arrowbase", "head");
    model.result("pg1").feature("pttraj3").set("arrowscale", "10");
    model.result("pg1").feature("pttraj3").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj3")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj3").set("titletype", "none");
    model.result("pg1").feature("pttraj3").label("\u5f84\u5411\u6eda\u5b50\u8f74\u627f 2");
    model.result("pg1").feature("pttraj3").create("def", "Deform");
    model.result("pg1").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8f68\u9053");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "manualindices", 1);
    model.result("pg2").setIndex("looplevelindices", 1, 1);
    model.result("pg2").set("preserveaspect", true);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5706\u76d8\u548c\u8f74\u627f\u4e0a\u7684\u8f68\u9053");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1, 2);
    model.result("pg2").feature("ptgr1").set("expr", "w/C");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "v/C");
    model.result("pg2").feature("ptgr1").set("linewidth", 2);
    model.result("pg2").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u8f74\u627f", 0);
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u5706\u76d8", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelindices", 2, 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelindices", 3, 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u9891\u8c31 w");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(1);
    model.result("pg3").feature("ptgr1").set("expr", "w");
    model.result("pg3").feature("ptgr1").set("xdata", "fourier");
    model.result("pg3").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg3").feature("ptgr1").set("nfreqsactive", true);
    model.result("pg3").feature("ptgr1").set("nfreqs", 400);
    model.result("pg3").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("autopoint", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmax", 600);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u8f74\u627f\u529b");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"rotbm.rrb1.Fsz"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u8f74 (rrb1) \u4e0a\u7684\u529b\uff0cz \u5206\u91cf"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg4").feature("glob1").set("autodescr", false);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u627f\u529b\u77e9");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"rotbm.rrb1.Msy"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u8f74 (rrb1) \u4e0a\u7684\u529b\u77e9\uff0cy \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();

    model.batch("b1").feature("daDef").set("operation", "clearalldata");
    model.batch("b1").feature("daDef").run();

    model.result("pg2").run();

    model
         .title("\u6eda\u5b50\u8f74\u627f\u95f4\u9699\u5bf9\u8f6c\u5b50\u975e\u540c\u6b65\u632f\u52a8\u7684\u5f71\u54cd");

    model
         .description("\u4e3a\u907f\u514d\u8f6c\u5b50\u4ea7\u751f\u975e\u540c\u6b65\u632f\u52a8\uff0c\u5e94\u4f7f\u8f74\u627f\u95f4\u9699\u4fdd\u6301\u6700\u5c0f\uff1b\u7136\u800c\uff0c\u95f4\u9699\u8fc7\u5c0f\u53c8\u4f1a\u964d\u4f4e\u8f74\u627f\u7684\u8010\u4e45\u6027\u3002\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u4e3a\u4e0d\u540c\u5f84\u5411\u95f4\u9699\u7684\u975e\u7ebf\u6027\u63a5\u89e6\u5f15\u8d77\u7684\u632f\u52a8\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("nonsynchronous_rotor_vibration_with_roller_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
