/*
 * pesticide_transport.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:32 by COMSOL 6.3.0.290. */
public class pesticide_transport {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Solute_Transport");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k_1", "0.36[1/d]", "\u901f\u7387\u5e38\u6570\uff0c\u53cd\u5e94 1");
    model.param().set("k_2", "0.024[1/d]", "\u901f\u7387\u5e38\u6570\uff0c\u53cd\u5e94 2");
    model.param().set("k_3", "0.2[1/d]", "\u901f\u7387\u5e38\u6570\uff0c\u53cd\u5e94 3");
    model.param().set("k_4", "0.01[1/d]", "\u901f\u7387\u5e38\u6570\uff0c\u53cd\u5e94 4");
    model.param().set("k_5", "0.0524[1/d]", "\u901f\u7387\u5e38\u6570\uff0c\u53cd\u5e94 5");
    model.param().label("\u901f\u7387\u5e38\u6570");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ge").feature("ge1")
         .set("name", new String[]{"cpm_a", "cpm_asx", "cpm_asn", "cpm_ao", "cpm_asxo", "cpm_asno"});
    model.component("comp1").physics("ge").feature("ge1")
         .set("equation", new String[]{"cpm_at+k_1*cpm_a+k_3*cpm_a", "cpm_asxt-k_1*cpm_a+k_2*cpm_asx+k_4*cpm_asx", "cpm_asnt-k_2*cpm_asx+k_5*cpm_asn", "cpm_aot-k_3*cpm_a", "cpm_asxot-k_4*cpm_asx", "cpm_asnot-k_5*cpm_asn"});
    model.component("comp1").physics("ge").feature("ge1").set("initialValueU", new int[]{1, 0, 0, 0, 0, 0});
    model.component("comp1").physics("ge").feature("ge1").set("initialValueUt", new int[]{0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("ge").feature("ge1")
         .set("description", new String[]{"\u6d95\u706d\u5a01", "\u6d95\u706d\u5a01\u4e9a\u781c", "\u6d95\u706d\u5a01\u781c", "\u6d95\u706d\u5a01\u809f", "\u6d95\u706d\u5a01\u4e9a\u781c\u809f", "\u6d95\u706d\u5a01\u781c\u809f"});
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "concentration");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "reactionrate");

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,1,100)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1")
         .set("expr", new String[]{"cpm_a", "cpm_asx", "cpm_asn", "cpm_ao", "cpm_asxo", "cpm_asno"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u6d95\u706d\u5a01", "\u6d95\u706d\u5a01\u4e9a\u781c", "\u6d95\u706d\u5a01\u781c", "\u6d95\u706d\u5a01\u809f", "\u6d95\u706d\u5a01\u4e9a\u781c\u809f", "\u6d95\u706d\u5a01\u781c\u809f"});
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"cpm_a", "cpm_asx", "cpm_asn", "cpm_ao", "cpm_asxo", "cpm_asno"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d95\u706d\u5a01", "\u6d95\u706d\u5a01\u4e9a\u781c", "\u6d95\u706d\u5a01\u781c", "\u6d95\u706d\u5a01\u809f", "\u6d95\u706d\u5a01\u4e9a\u781c\u809f", "\u6d95\u706d\u5a01\u781c\u809f"});
    model.result("pg1").run();
    model.result("pg1").label("\u7269\u8d28\u7684\u6d53\u5ea6\uff08100 \u5929\uff09");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "t");
    model.result("pg1").feature("glob1").set("xdataunit", "d");
    model.result("pg1").feature("glob1").setIndex("expr", "cpm_a+cpm_asn+cpm_asx", 6);
    model.result("pg1").feature("glob1")
         .setIndex("descr", "\u6bd2\u6027\u6700\u5927\u7684\u7269\u8d28\u603b\u548c", 6);
    model.result("pg1").run();

    model.component("comp1").physics().create("dl", "PorousMediaFlowRichards", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/dl", false);

    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/tds", false);

    model.component("comp1").physics("tds").field("concentration").field("c_a");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"c_a", "c_asx", "c_asn"});

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ge", false);
    model.study("std2").feature("time").setSolveFor("/physics/dl", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("rhof", "1e3[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param("par2").set("poro_1", "0.339", "\u5b54\u9699\u7387\uff0c\u5c42 1");
    model.param("par2").set("poro_2", "0.399", "\u5b54\u9699\u7387\uff0c\u5c42 2");
    model.param("par2").set("thetar_1", "0.001", "\u6b8b\u4f59\u9971\u548c\u5ea6\uff0c\u5c42 1");
    model.param("par2").set("thetar_2", "0.001", "\u6b8b\u4f59\u9971\u548c\u5ea6\uff0c\u5c42 2");
    model.param("par2").set("Sp_1", "0.339[1/m]/(g_const*rhof)", "\u50a8\u6c34\u7cfb\u6570\uff0c\u5c42 1");
    model.param("par2").set("Sp_2", "0.399[1/m]/(g_const*rhof)", "\u50a8\u6c34\u7cfb\u6570\uff0c\u5c42 2");
    model.param("par2").set("Ks_1", "0.454[m/d]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u5c42 1");
    model.param("par2").set("Ks_2", "0.298[m/d]", "\u9971\u548c\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u5c42 2");
    model.param("par2").set("alpha_1", "1.39[1/m]", "Van Genuchten \u53c2\u6570 \u03b1\uff0c\u5c42 1");
    model.param("par2").set("alpha_2", "1.74[1/m]", "Van Genuchten \u53c2\u6570 \u03b1\uff0c\u5c42 2");
    model.param("par2").set("n_1", "1.6", "Van Genuchten \u53c2\u6570 n\uff0c\u5c42 1");
    model.param("par2").set("n_2", "1.38", "Van Genuchten \u53c2\u6570 n\uff0c\u5c42 2");
    model.param("par2").set("Hp0", "0.01[m]", "\u73af\u5f62\u6c34\u6c60\u4e2d\u7684\u538b\u529b\u6c34\u5934");
    model.param("par2").set("N0", "0.01*Ks_1*rhof", "\u5e95\u90e8\u6cc4\u6f0f\u91cf");
    model.param("par2").set("c0", "1[mol/m^3]", "\u73af\u5f62\u6c34\u6c60\u4e2d\u7684\u6eb6\u8d28\u6d53\u5ea6");
    model.param("par2").set("rhob", "1300[kg/m^3]", "\u672c\u4f53\u5bc6\u5ea6");
    model.param("par2").set("kp_a", "1e-4[m^3/kg]", "\u5206\u914d\u7cfb\u6570\uff0c\u6d95\u706d\u5a01");
    model.param("par2")
         .set("kp_asx", "0.5e-4[m^3/kg]", "\u5206\u914d\u7cfb\u6570\uff0c\u6d95\u706d\u5a01\u4e9a\u781c");
    model.param("par2").set("kp_asn", "2e-4[m^3/kg]", "\u5206\u914d\u7cfb\u6570\uff0c\u6d95\u706d\u5a01\u781c");
    model.param("par2").set("Dl", "3.74e-3[m^2/d]", "\u6269\u6563\u7cfb\u6570\uff0c\u6db2\u76f8");
    model.param("par2").set("Dg", "0.432[m^2/d]", "\u6269\u6563\u7cfb\u6570\uff0c\u6c14\u76f8");
    model.param("par2").set("alphar", "0.005[m]", "\u7eb5\u5411\u5f25\u6563\u5ea6");
    model.param("par2").set("alphaz", "0.001[m]", "\u6a2a\u5411\u5f25\u6563\u5ea6");
    model.param("par2").set("kg_a", "1.33e-7", "\u6325\u53d1");
    model.param("par2").set("kg_asn", "1.33e-3", "\u6325\u53d1");
    model.param("par2").set("d_s", "0.05[m]", "\u6d53\u5ea6\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param("par2").label("\u4f20\u9012\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1.5, 0.9});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -1.3});
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.25, 0);
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{1.5, 0.4});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, -0.4});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.25, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(3, 4);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("r_1", "k_1*c_a", "\u901f\u7387\u8868\u8fbe\u5f0f 1");
    model.component("comp1").variable("var1").set("r_2", "k_2*c_asx", "\u901f\u7387\u8868\u8fbe\u5f0f 2");
    model.component("comp1").variable("var1").set("r_3", "k_3*c_a", "\u901f\u7387\u8868\u8fbe\u5f0f 3");
    model.component("comp1").variable("var1").set("r_4", "k_4*c_asx", "\u901f\u7387\u8868\u8fbe\u5f0f 4");
    model.component("comp1").variable("var1").set("r_5", "k_5*c_asn", "\u901f\u7387\u8868\u8fbe\u5f0f 5");

    model.component("comp1").physics("dl").feature("usporous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("usporous1").set("Sp", "Sp_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1")
         .set("Ks", new String[]{"Ks_1", "0", "0", "0", "Ks_1", "0", "0", "0", "Ks_1"});
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("alpha", "alpha_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("n", "n_1");
    model.component("comp1").physics("dl").feature("usporous1").feature("pm1").set("theta_r", "thetar_1");
    model.component("comp1").physics("dl").feature().duplicate("usporous2", "usporous1");
    model.component("comp1").physics("dl").feature("usporous2").selection().set(2, 4);
    model.component("comp1").physics("dl").feature("usporous2").set("Sp", "Sp_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1")
         .set("Ks", new String[]{"Ks_2", "0", "0", "0", "Ks_2", "0", "0", "0", "Ks_2"});
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("alpha", "alpha_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("n", "n_2");
    model.component("comp1").physics("dl").feature("usporous2").feature("pm1").set("theta_r", "thetar_2");
    model.component("comp1").physics("dl").feature("gr1").set("GravityType", "Elevation");
    model.component("comp1").physics("dl").feature("init1").set("InitType", "Hp");
    model.component("comp1").physics("dl").feature("init1").set("Hp", "-(z+1.2)");
    model.component("comp1").physics("dl").feature().duplicate("init2", "init1");
    model.component("comp1").physics("dl").feature("init2").selection().set(2, 4);
    model.component("comp1").physics("dl").feature("init2").set("Hp", "-(z+1.2)-0.2*(z+0.4)");
    model.component("comp1").physics("dl").create("ph1", "PressureHead", 1);
    model.component("comp1").physics("dl").feature("ph1").selection().set(5);
    model.component("comp1").physics("dl").feature("ph1").set("Hp0", "Hp0");
    model.component("comp1").physics("dl").create("pl1", "PerviousLayer", 1);
    model.component("comp1").physics("dl").feature("pl1").selection().set(2, 8);
    model.component("comp1").physics("dl").feature("pl1").set("Hb", -2);
    model.component("comp1").physics("dl").feature("pl1").set("Rb", "1/5[d]");
    model.component("comp1").physics("tds").create("usporous1", "UnsaturatedPorousMedium", 2);
    model.component("comp1").physics("tds").feature("usporous1").selection().all();
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("SaturationType", "LiquidVolumeFraction");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("theta_l", "dl.theta_l");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("FluidFractionTimeChange", "TimeChangeInPressureHead");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("dHpdt_src", "root.comp1.dl.dHpdt");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("Cm", "dl.Cm");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("DL_c_a", new String[]{"Dl", "0", "0", "0", "Dl", "0", "0", "0", "Dl"});
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("DL_c_asx", new String[]{"Dl", "0", "0", "0", "Dl", "0", "0", "0", "Dl"});
    model.component("comp1").physics("tds").feature("usporous1").feature("liquid1")
         .set("DL_c_asn", new String[]{"Dl", "0", "0", "0", "Dl", "0", "0", "0", "Dl"});
    model.component("comp1").physics("tds").feature("usporous1").feature("gas1")
         .set("DG_c_a", new String[]{"Dg", "0", "0", "0", "Dg", "0", "0", "0", "Dg"});
    model.component("comp1").physics("tds").feature("usporous1").feature("gas1")
         .set("DG_c_asx", new String[]{"Dg", "0", "0", "0", "Dg", "0", "0", "0", "Dg"});
    model.component("comp1").physics("tds").feature("usporous1").feature("gas1")
         .set("DG_c_asn", new String[]{"Dg", "0", "0", "0", "Dg", "0", "0", "0", "Dg"});
    model.component("comp1").physics("tds").feature("usporous1").feature("gas1").setIndex("kG", "kg_a", 0);
    model.component("comp1").physics("tds").feature("usporous1").feature("gas1").setIndex("kG", "kg_asn", 2);
    model.component("comp1").physics("tds").feature("usporous1").create("ads1", "Adsorptions", 2);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").set("SorptionType", "UserDefined");
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("cP", "kp_a*c_a", 0);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("cP", "kp_asx*c_asx", 1);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("species", true, 2);
    model.component("comp1").physics("tds").feature("usporous1").feature("ads1").setIndex("cP", "kp_asn*c_asn", 2);
    model.component("comp1").physics("tds").feature("usporous1").create("disp1", "Dispersion", 2);
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("DispersionTensor", "Dispersivity");
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("DispersivityModel", "TransverseIsotropic");
    model.component("comp1").physics("tds").feature("usporous1").feature("disp1")
         .set("alpha", new String[]{"alphar", "0", "alphaz"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().all();
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c_a", "dl.theta_l*(-r_1-r_3)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c_asx", "dl.theta_l*(r_1-r_2-r_4)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c_asn", "dl.theta_l*(r_2-r_5)", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(2, 8, 12, 13);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(5);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tds").create("vola1", "Volatilization", 1);
    model.component("comp1").physics("tds").feature("vola1").selection().set(6, 11);
    model.component("comp1").physics("tds").feature("vola1").set("hc", "Dg/d_s");
    model.component("comp1").physics("tds").feature("vola1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("vola1").setIndex("species", true, 2);

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").label("\u591a\u5b54\u6750\u6599\uff1a\u4e0b\u5c42");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature("fluid1").propertyGroup("def")
         .set("density", new String[]{"rhof"});
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-poro_1");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"rhob"});
    model.component("comp1").material().duplicate("pmat2", "pmat1");
    model.component("comp1").material("pmat2").label("\u591a\u5b54\u6750\u6599\uff1a\u4e0a\u5c42");
    model.component("comp1").material("pmat2").selection().set(2, 4);
    model.component("comp1").material("pmat2").feature("solid1").set("vfrac", "1-poro_2");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").run();

    model.study("std2").feature("time").set("tunit", "d");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,0.9) range(1,1,10)");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1")
         .set("defaultPlotIDs", new String[]{"PhysicsInterfaces_PorousMediaFlow/icom6/pdef1/pcond4/pg2|dl"});
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b\uff0c\u4e09\u7ef4 (dl)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u538b\u529b\uff0c\u4e09\u7ef4 (dl)");
    model.result("pg2").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(1, 2);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Viridis");
    model.result("pg2").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "dl.Hp");
    model.result("pg2").feature("con1").set("descr", "\u538b\u529b\u6c34\u5934");
    model.result("pg2").feature("con1").set("number", 8);
    model.result("pg2").feature("con1").set("colortable", "GrayScale");
    model.result("pg2").feature("con1").set("contourlabels", true);
    model.result("pg2").feature("con1").set("labelcolor", "white");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6709\u6548\u9971\u548c\u5ea6 (dl)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 20, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "dl.Se");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("contourtype", "filled");
    model.result("pg3").feature("con1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("con1").set("colortabletrans", "reverse");
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").label("\u6709\u6548\u9971\u548c\u5ea6 (dl)");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").run();
    model.result("pg3").feature("con1").set("number", 15);
    model.result("pg3").run();
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 20, 0);
    model.result("pg4").label("\u6d53\u5ea6, a (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c_a"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_c_ar", "tds.tflux_c_az"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg4").label("\u6d53\u5ea6, a (tds)");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff0c\u6d95\u706d\u5a01");
    model.result("pg4").run();
    model.result("pg4").feature().remove("arws1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", 0);
    model.result("pg4").feature("surf1").stepPrevious(0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 15, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").run();
    model.result("pg4").stepLast(0);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6d53\u5ea6\uff0c\u6d95\u706d\u5a01\u4e9a\u781c");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "c_asx");
    model.result("pg5").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc_asx");
    model.result("pg5").feature("surf1").set("rangecolormax", 0.5);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 15, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").run();
    model.result("pg5").stepLast(0);
    model.result("pg5").run();
    model.result("pg2").run();

    model.title("\u6740\u866b\u5242\u5728\u571f\u58e4\u4e2d\u7684\u8fd0\u79fb\u548c\u53cd\u5e94");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u6740\u866b\u5242\u6d95\u706d\u5a01\u7684\u964d\u89e3\u3002\u5176\u4e2d\u7684\u6c28\u57fa\u7532\u9178\u76d0\u9996\u5148\u964d\u89e3\u6210\u781c\uff0c\u968f\u540e\u5206\u89e3\u6210\u4e9a\u781c\u3002\u751f\u7269\u964d\u89e3\u53cd\u5e94\u662f\u51cf\u5c11\u6709\u6bd2\u7269\u8d28\u542b\u91cf\u7684\u91cd\u8981\u9014\u5f84\u3002\u5728\u5efa\u6a21\u7684\u7b2c\u4e8c\u9636\u6bb5\u4f7f\u7528\u8ba1\u7b97\u597d\u7684\u53cd\u5e94\u52a8\u529b\u5b66\uff0c\u5728\u7a7a\u95f4\u4e0a\u6a21\u62df\u4e86\u6d95\u706d\u5a01\u7684\u8fd0\u79fb\u548c\u53cd\u5e94\u5bf9\u571f\u58e4\u7684\u6c61\u67d3\u8fc7\u7a0b\u3002");

    model.label("pesticide_transport.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
