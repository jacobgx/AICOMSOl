/*
 * egofet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:55 by COMSOL 6.3.0.290. */
public class egofet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcc", "TransportOfChargeCarriers", "geom1");
    model.component("comp1").physics("tcc").field("numberdensity").field("h");
    model.component("comp1").physics("tcc").field("numberdensity").component(new String[]{"h"});
    model.component("comp1").physics().create("tcc2", "TransportOfChargeCarriers", "geom1");
    model.component("comp1").physics("tcc2").field("numberdensity").field("p");
    model.component("comp1").physics("tcc2").field("numberdensity").component(new String[]{"p", "n"});
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcc2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.param().set("w_dom", "30[um]");
    model.param().descr("w_dom", "\u57df\u5bbd\u5ea6");
    model.param().set("h_semi", "500[nm]");
    model.param().descr("h_semi", "\u6709\u673a\u534a\u5bfc\u4f53\u539a\u5ea6");
    model.param().set("h_electrolyte", "10[um]");
    model.param().descr("h_electrolyte", "\u7535\u89e3\u8d28\u539a\u5ea6");
    model.param().set("V_dr", "0[V]");
    model.param().descr("V_dr", "\u6f0f\u6781\u7535\u538b");
    model.param().set("V_g", "0[V]");
    model.param().descr("V_g", "\u6805\u6781\u7535\u538b");
    model.param().set("D_h", "1e-6[m^2/s]");
    model.param().descr("D_h", "\u7a7a\u7a74\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_p", "15e-10[m^2/s]");
    model.param().descr("D_p", "\u6b63\u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_n", "D_p");
    model.param().descr("D_n", "\u8d1f\u79bb\u5b50\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("T", "300[K]");
    model.param().descr("T", "\u6e29\u5ea6");
    model.param().set("d", "1[cm]");
    model.param().descr("d", "\u6df1\u5ea6");
    model.param().set("z_h", "+1");
    model.param().descr("z_h", "\u7a7a\u7a74\u7535\u8377");
    model.param().set("z_p", "+1");
    model.param().descr("z_p", "\u6b63\u79bb\u5b50\u7535\u8377");
    model.param().set("z_n", "-1");
    model.param().descr("z_n", "\u8d1f\u79bb\u5b50\u7535\u8377");
    model.param().set("ch0", "1e-3[mol/m^3]");
    model.param().descr("ch0", "\u7a7a\u7a74\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cp0", "1[mol/m^3]");
    model.param().descr("cp0", "\u6b63\u79bb\u5b50\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cn0", "1[mol/m^3]");
    model.param().descr("cn0", "\u8d1f\u79bb\u5b50\u521d\u59cb\u6d53\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_dom", "h_semi"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_dom", ""});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "h_semi"});
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_dom", "h_electrolyte"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcc").label("\u7a7a\u7a74\u4f20\u9012");
    model.component("comp1").physics("tcc").selection().set(1);
    model.component("comp1").physics("tcc").prop("PhysicalModel").set("dz", "d");
    model.component("comp1").physics("tcc").feature("tp1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("tcc").feature("tp1").setIndex("z_h", "z_h", 0);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"D_h*F_const/(R_const*T)", "0", "0", "0", "D_h*F_const/(R_const*T)", "0", "0", "0", "D_h*F_const/(R_const*T)"}, 0);
    model.component("comp1").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"D_h", "0", "0", "0", "D_h", "0", "0", "0", "D_h"}, 0);
    model.component("comp1").physics("tcc").feature("init1").setIndex("n0", "ch0*F_const/e_const", 0);
    model.component("comp1").physics("tcc").create("nflx1", "NoFlux", 1);
    model.component("comp1").physics("tcc").feature("nflx1").selection().all();
    model.component("comp1").physics("tcc").create("nd1", "NumberDensity", 1);
    model.component("comp1").physics("tcc").feature("nd1").selection().set(1, 6);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("carrier", true, 0);
    model.component("comp1").physics("tcc").feature("nd1").setIndex("n0", "ch0*F_const/e_const", 0);
    model.component("comp1").physics("tcc").create("ccal1", "CurrentCalculation", 1);
    model.component("comp1").physics("tcc").feature("ccal1").selection().set(6);
    model.component("comp1").physics("tcc2").selection().set(2);
    model.component("comp1").physics("tcc2").prop("PhysicalModel").set("dz", "d");
    model.component("comp1").physics("tcc2").feature("tp1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("tcc2").feature("tp1").setIndex("z_p", "z_p", 0);
    model.component("comp1").physics("tcc2").feature("tp1").setIndex("z_n", "z_n", 0);
    model.component("comp1").physics("tcc2").feature("tp1")
         .setIndex("mu", new String[]{"D_p*F_const/(R_const*T)", "0", "0", "0", "D_p*F_const/(R_const*T)", "0", "0", "0", "D_p*F_const/(R_const*T)"}, 0);
    model.component("comp1").physics("tcc2").feature("tp1")
         .setIndex("mu", new String[]{"D_n*F_const/(R_const*T)", "0", "0", "0", "D_n*F_const/(R_const*T)", "0", "0", "0", "D_n*F_const/(R_const*T)"}, 1);
    model.component("comp1").physics("tcc2").feature("tp1")
         .setIndex("D", new String[]{"D_p", "0", "0", "0", "D_p", "0", "0", "0", "D_p"}, 0);
    model.component("comp1").physics("tcc2").feature("tp1")
         .setIndex("D", new String[]{"D_n", "0", "0", "0", "D_n", "0", "0", "0", "D_n"}, 1);
    model.component("comp1").physics("tcc2").feature("init1").setIndex("n0", "cp0*F_const/e_const", 0);
    model.component("comp1").physics("tcc2").feature("init1").setIndex("n0", "cn0*F_const/e_const", 1);
    model.component("comp1").physics("tcc2").create("nflx1", "NoFlux", 1);
    model.component("comp1").physics("tcc2").feature("nflx1").selection().all();
    model.component("comp1").physics("tcc2").create("nd1", "NumberDensity", 1);
    model.component("comp1").physics("tcc2").feature("nd1").selection().set(3, 7);
    model.component("comp1").physics("tcc2").feature("nd1").setIndex("carrier", true, 0);
    model.component("comp1").physics("tcc2").feature("nd1").setIndex("n0", "cp0*F_const/e_const", 0);
    model.component("comp1").physics("tcc2").feature("nd1").setIndex("carrier", true, 1);
    model.component("comp1").physics("tcc2").feature("nd1").setIndex("n0", "cn0*F_const/e_const", 1);
    model.component("comp1").physics("es").prop("d").set("d", "d");
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 2);
    model.component("comp1").physics("es").feature("ccnf1").selection().all();
    model.component("comp1").physics("es").feature("ccnf1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("es").feature("ccnf1").set("epsilonr", new int[]{3, 0, 0, 0, 3, 0, 0, 0, 3});
    model.component("comp1").physics("es").feature().duplicate("ccnf2", "ccnf1");
    model.component("comp1").physics("es").feature("ccnf2").selection().set(2);
    model.component("comp1").physics("es").feature("ccnf2").set("epsilonr", new int[]{79, 0, 0, 0, 79, 0, 0, 0, 79});
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").label("V_s");
    model.component("comp1").physics("es").feature("pot1").selection().set(1);
    model.component("comp1").physics("es").feature().duplicate("pot2", "pot1");
    model.component("comp1").physics("es").feature("pot2").label("V_dr");
    model.component("comp1").physics("es").feature("pot2").selection().set(6);
    model.component("comp1").physics("es").feature("pot2").set("V0", "V_dr");
    model.component("comp1").physics("es").feature().duplicate("pot3", "pot2");
    model.component("comp1").physics("es").feature("pot3").label("V_g");
    model.component("comp1").physics("es").feature("pot3").selection().set(5);
    model.component("comp1").physics("es").feature("pot3").set("V0", "V_g");
    model.component("comp1").physics("es").create("scd1", "SpaceChargeDensity", 2);
    model.component("comp1").physics("es").feature("scd1").selection().set(1);
    model.component("comp1").physics("es").feature("scd1").set("rhoq", "h*e_const");
    model.component("comp1").physics("es").feature().duplicate("scd2", "scd1");
    model.component("comp1").physics("es").feature("scd2").selection().set(2);
    model.component("comp1").physics("es").feature("scd2").set("rhoq", "(p-n)*e_const");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1e-7[m]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 1000);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("numelem", 50);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4, 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.05);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "w_dom", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "w_dom", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "V_g", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,-0.05,-0.5)", 0);
    model.study("std1").feature("stat").setIndex("pname", "w_dom", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "w_dom", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "m", 1);
    model.study("std1").feature("stat").setIndex("pname", "V_dr", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,-0.05,-0.5)", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").label("\u8f7d\u6d41\u5b50 h (tcc)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "\u8f7d\u6d41\u5b50 h:");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"tcc.n_h"});
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tcc.Jc_hx", "tcc.Jc_hy"});
    model.result("pg1").feature("arws1").set("placement", "elements");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").setIndex("looplevel", 11, 1);
    model.result("pg2").label("\u8f7d\u6d41\u5b50 p (tcc2)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "\u8f7d\u6d41\u5b50 p:");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"tcc2.n_p"});
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"tcc2.Jc_px", "tcc2.Jc_py"});
    model.result("pg2").feature("arws1").set("placement", "elements");
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(2);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").setIndex("looplevel", 11, 1);
    model.result("pg3").label("\u8f7d\u6d41\u5b50 n (tcc2)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "\u8f7d\u6d41\u5b50 n:");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"tcc2.n_n"});
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tcc2.Jc_nx", "tcc2.Jc_ny"});
    model.result("pg3").feature("arws1").set("placement", "elements");
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(2);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u52bf (es)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "V");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "V");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u573a (es)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "es.normE");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("method", "pointdir");
    model.result().dataset("cln1").set("pdpoint", new String[]{"w_dom/2", "0"});
    model.result().dataset("cln1").set("pddir", new int[]{0, 1});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("V-y");
    model.result("pg6").setIndex("looplevelinput", "first", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").set("data", "cln1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "V");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("E-y");
    model.result("pg7").set("legendpos", "uppermiddle");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "es.normE");
    model.result("pg7").feature("lngr1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("h-y");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "h");
    model.result("pg8").run();
    model.result("pg8").set("ylog", true);
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("p-y");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "p");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("n-y");
    model.result("pg10").run();
    model.result("pg10").feature("lngr1").set("expr", "n");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("I_dr-V_dr");
    model.result("pg11").setIndex("looplevelinput", "manualindices", 1);
    model.result("pg11").setIndex("looplevelindices", "range(1,2,11)", 1);
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"tcc.I0_0"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u4f20\u5bfc\u7535\u6d41"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg11").feature("glob1").setIndex("expr", "-tcc.I0_0", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "-\u6f0f\u6781\u7535\u6d41", 0);
    model.result("pg11").feature("glob1").set("xdata", "expr");
    model.result("pg11").feature("glob1").set("xdataexpr", "-V_dr");
    model.result("pg11").feature("glob1").set("xdatadescractive", true);
    model.result("pg11").feature("glob1").set("xdatadescr", "-\u6f0f\u6781\u7535\u6d41");
    model.result("pg11").feature("glob1").set("autodescr", false);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("I_dr-V_g");
    model.result("pg12").setIndex("looplevelinput", "all", 1);
    model.result("pg12").setIndex("looplevelinput", "manual", 0);
    model.result("pg12").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg12").run();
    model.result("pg12").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg12").feature("glob1").set("xdataexpr", "-V_g");
    model.result("pg12").run();

    model.title("\u7535\u89e3\u8d28\u6805\u6781\u6709\u673a\u573a\u6548\u5e94\u6676\u4f53\u7ba1");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u4e00\u822c\u7684\u6f02\u79fb-\u6269\u6563\u6a21\u578b\u6765\u6a21\u62df\u7535\u89e3\u8d28\u6805\u6781\u6709\u673a\u573a\u6548\u5e94\u6676\u4f53\u7ba1\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u8f7d\u6d41\u5b50\u4f20\u8f93\u201d\u548c\u201c\u9759\u7535\u201d\u63a5\u53e3\uff0c\u5e76\u663e\u793a\u6676\u4f53\u7ba1\u7279\u6027\u7684\u53ef\u89c6\u5316\u6548\u679c\u3002\u5728\u6a21\u62df\u7684\u5668\u4ef6\u4e2d\uff0cEDL \u7684\u5f62\u6210\u8fc7\u7a0b\u63ed\u793a\u4e86 EGOFET \u7684\u5173\u952e\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("egofet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
