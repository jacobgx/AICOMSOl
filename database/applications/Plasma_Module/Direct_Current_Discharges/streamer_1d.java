/*
 * streamer_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:03 by COMSOL 6.3.0.290. */
public class streamer_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Direct_Current_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 1.15, 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("i2", "Interval");
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", 1.15, 0);
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", 1.151, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("ne0", "ne0max*exp(-((x-x0)/sigma)^2)+ne0min");
    model.component("comp1").variable("var1").set("ne0min", "1e-15[m^-3]");
    model.component("comp1").variable("var1").set("ne0max", "5e16[m^-3]");
    model.component("comp1").variable("var1").set("x0", "0.02e-3[m]");
    model.component("comp1").variable("var1").set("sigma", "0.01e-4[m]");

    model.param().set("Efield", "-100[kV/cm]");

    model.component("comp1").physics("plas").prop("Stabilization").set("SourceStabilization", false);
    model.component("comp1").physics("plas").prop("Stabilization").set("ReactionSourceStabilization", false);
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").prop("ElectronProperties")
         .set("MeanElectronEnergyModel", "LocalFieldApproximationE");
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 1);
    model.component("comp1").physics("plas").feature("eir1").set("formula", "e+N=>2e+N+");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir1").set("de", 15.6);
    model.component("comp1").physics("plas").feature("eir1").set("SpecifyReactionUsing", "UseLookupTable");
    model.component("comp1").physics("plas").feature("eir1").set("RateConstantForm", "UseTownsend");
    model.component("comp1").physics("plas").feature("eir1")
         .set("xtownratedata", new double[]{0, 0.013061613, 0.093540084, 0.228107915, 0.3849651, 0.548393454, 0.711943481, 0.873094428, 1.030952274, 1.185305533, 1.33622818, 1.483907137, 1.62856571, 1.770429612, 1.909712486, 2.046610487, 2.181301066, 2.313943661, 2.444681161, 2.57364161, 3.786025481, 4.898764283, 5.944640553, 6.941089171, 7.898753833, 8.824740665, 9.724098546, 10.6005823, 11.45708358, 12.29589151, 13.1188591, 13.92751417, 14.72313607, 15.50681025, 16.27946821, 17.04191719});
    model.component("comp1").physics("plas").feature("eir1")
         .set("ytownratedata", new String[]{"0", "8.90E-30", "2.24E-32", "1.54E-31", "2.36E-30", "2.81E-29", "2.28E-28", "1.31E-27", "5.69E-27", "1.97E-26", 
         "5.68E-26", "1.42E-25", "3.14E-25", "6.30E-25", "1.17E-24", "2.02E-24", "3.31E-24", "5.16E-24", "7.71E-24", "1.11E-23", 
         "1.23E-22", "4.33E-22", "9.47E-22", "1.61E-21", "2.38E-21", "3.20E-21", "4.03E-21", "4.87E-21", "5.70E-21", "6.50E-21", 
         "7.28E-21", "8.02E-21", "8.74E-21", "9.43E-21", "1.01E-20", "1.07E-20"});
    model.component("comp1").physics("plas").feature("N").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("N").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("N_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("N_1p").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "N+=>N");
    model.component("comp1").physics("plas").feature("sr1").selection().all();
    model.component("comp1").physics("plas").feature("sr1").selection().set(1, 2);
    model.component("comp1").physics("plas").feature("pes1")
         .set("SpecifyElectronDensityAndEnergy", "UseLookupTables");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muNXdata", new double[]{0, 0.013061613, 0.093540084, 0.228107915, 0.3849651, 0.548393454, 0.711943481, 0.873094428, 1.030952274, 1.185305533, 1.33622818, 1.483907137, 1.62856571, 1.770429612, 1.909712486, 2.046610487, 2.181301066, 2.313943661, 2.444681161, 2.57364161, 3.786025481, 4.898764283, 5.944640553, 6.941089171, 7.898753833, 8.824740665, 9.724098546, 10.6005823, 11.45708358, 12.29589151, 13.1188591, 13.92751417, 14.72313607, 15.50681025, 16.27946821, 17.04191719});
    model.component("comp1").physics("plas").feature("pes1")
         .set("muNYdata", new String[]{"0", "1.86E+24", "1.65E+24", "1.46E+24", "1.34E+24", "1.26E+24", "1.21E+24", "1.17E+24", "1.14E+24", "1.12E+24", 
         "1.11E+24", "1.10E+24", "1.09E+24", "1.08E+24", "1.08E+24", "1.08E+24", "1.07E+24", "1.07E+24", "1.07E+24", "1.07E+24", 
         "1.09E+24", "1.12E+24", "1.15E+24", "1.18E+24", "1.21E+24", "1.23E+24", "1.25E+24", "1.28E+24", "1.30E+24", "1.32E+24", 
         "1.34E+24", "1.35E+24", "1.37E+24", "1.39E+24", "1.40E+24", "1.42E+24"});
    model.component("comp1").physics("plas").feature("pes1")
         .set("deNXdata", new double[]{0, 0.013061613, 0.093540084, 0.228107915, 0.3849651, 0.548393454, 0.711943481, 0.873094428, 1.030952274, 1.185305533, 1.33622818, 1.483907137, 1.62856571, 1.770429612, 1.909712486, 2.046610487, 2.181301066, 2.313943661, 2.444681161, 2.57364161, 3.786025481, 4.898764283, 5.944640553, 6.941089171, 7.898753833, 8.824740665, 9.724098546, 10.6005823, 11.45708358, 12.29589151, 13.1188591, 13.92751417, 14.72313607, 15.50681025, 16.27946821, 17.04191719});
    model.component("comp1").physics("plas").feature("pes1")
         .set("deNYdata", new String[]{"0", "7.52E+23", "7.36E+23", "7.40E+23", "7.68E+23", "8.11E+23", "8.62E+23", "9.18E+23", "9.80E+23", "1.04E+24", 
         "1.11E+24", "1.18E+24", "1.25E+24", "1.32E+24", "1.40E+24", "1.47E+24", "1.55E+24", "1.62E+24", "1.70E+24", "1.78E+24", 
         "2.60E+24", "3.47E+24", "4.37E+24", "5.31E+24", "6.28E+24", "7.27E+24", "8.29E+24", "9.32E+24", "1.04E+25", "1.14E+25", 
         "1.25E+25", "1.36E+25", "1.47E+25", "1.58E+25", "1.70E+25", "1.81E+25"});
    model.component("comp1").physics("plas").feature("pes1").set("SpecifyMeanElectronEnergy", "MeanEnergyTable");
    model.component("comp1").physics("plas").feature("pes1")
         .set("enrgXdata", new int[]{0, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900});
    model.component("comp1").physics("plas").feature("pes1")
         .set("enrgYdata", new double[]{0, 0.013061613, 0.093540084, 0.228107915, 0.3849651, 0.548393454, 0.711943481, 0.873094428, 1.030952274, 1.185305533, 1.33622818, 1.483907137, 1.62856571, 1.770429612, 1.909712486, 2.046610487, 2.181301066, 2.313943661, 2.444681161, 2.57364161, 3.786025481, 4.898764283, 5.944640553, 6.941089171, 7.898753833, 8.824740665, 9.724098546, 10.6005823, 11.45708358, 12.29589151, 13.1188591, 13.92751417, 14.72313607, 15.50681025, 16.27946821, 17.04191719});
    model.component("comp1").physics("plas").feature("init1").set("neinit", "ne0");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("plas").feature("wall1").selection().all();
    model.component("comp1").physics("plas").create("gnd1", "Ground", 0);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(1);
    model.component("comp1").physics("plas").create("df1", "DisplacementField", 0);
    model.component("comp1").physics("plas").feature("df1")
         .set("D0", new String[]{"Efield*epsilon0_const", "0", "0"});
    model.component("comp1").physics("plas").feature("df1").selection().all();
    model.component("comp1").physics("plas").create("ccn1", "ChargeConservation", 1);
    model.component("comp1").physics("plas").feature("ccn1").selection().all();
    model.component("comp1").physics("plas").feature("ccn1").selection().set(2);
    model.component("comp1").physics("plas").feature("ccn1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("plas").feature("ccn1")
         .set("epsilonr", new int[]{10, 0, 0, 0, 10, 0, 0, 0, 10});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 2000);
    model.component("comp1").mesh("mesh1").feature().duplicate("edg2", "edg1");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,0.9/19,0.9)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"plas.ne"});

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e26\u7535\u7269\u8d28");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5bc6\u5ea6 (m<sup>-3</sup>)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("ymin", 0);
    model.result("pg1").set("ymax", "1.8e19");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("\u7535\u5b50");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").label("\u79bb\u5b50");
    model.result("pg1").feature("lngr2").set("expr", "plas.n_wN_1p");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{3, 9, 14, 20}, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf");
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{3, 9, 14, 20}, 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("ymin", -0.1);
    model.result("pg2").set("ymax", 9);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "plas.ebar");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7a7a\u95f4\u7535\u8377");
    model.result("pg3").set("ymin", -2);
    model.result("pg3").set("ymax", 2);
    model.result("pg3").set("legendpos", "upperright");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "plas.scharge");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{9, 14, 20}, 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u901f\u5ea6 (m/s)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").label("\u6f02\u79fb\u901f\u5ea6");
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", "plas.mflux_nex/plas.ne");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").label("\u89e3\u6790");
    model.result("pg4").feature("lngr2").selection().set(1);
    model.result("pg4").feature("lngr2")
         .set("expr", "plas.muexx*abs(plas.Ex)+2*sqrt(plas.Dexx*plas.muexx*abs(plas.Ex)*plas.alpha_1*plas.Nn)");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "x");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("linecolor", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u573a");
    model.result("pg5").set("ymin", -102);
    model.result("pg5").set("ymax", 2);
    model.result("pg5").set("legendpos", "upperright");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "plas.Ex");
    model.result("pg5").feature("lngr1").set("unit", "kV/cm");
    model.result("pg5").run();

    model.title("\u6c2e\u4e2d\u7684\u8d1f\u6d41\u6ce8");

    model
         .description("\u6d41\u6ce8\u662f\u4e00\u79cd\u5728\u5f3a\u7535\u573a\u4f5c\u7528\u4e0b\uff0c\u5728\u975e\u5bfc\u7535\u80cc\u666f\u4e2d\u53d1\u5c55\u7684\u77ac\u6001\u4e1d\u72b6\u653e\u7535\u73b0\u8c61\u3002\u8fd9\u7c7b\u653e\u7535\u80fd\u591f\u8fbe\u5230\u9ad8\u7535\u5b50\u6570\u5bc6\u5ea6\uff0c\u56e0\u6b64\uff0c\u53ef\u4ee5\u4ea7\u751f\u4e0e\u8bb8\u591a\u5e94\u7528\u76f8\u5173\u7684\u9ad8\u6d53\u5ea6\u6d3b\u6027\u5316\u5b66\u7269\u8d28\u3002\u5de5\u4e1a\u5e94\u7528\u5305\u62ec\u81ed\u6c27\u751f\u4ea7\u3001\u6c61\u67d3\u63a7\u5236\u548c\u8868\u9762\u5904\u7406\u7b49\u3002\n\n\u6d41\u6ce8\u7684\u4f20\u64ad\u7531\u9ad8\u5ea6\u975e\u7ebf\u6027\u52a8\u529b\u5b66\u6240\u9a71\u52a8\uff0c\u8fd9\u4e00\u8fc7\u7a0b\u6d89\u53ca\u975e\u5e38\u9661\u5ced\u7684\u5bc6\u5ea6\u68af\u5ea6\u4ee5\u53ca\u5206\u5e03\u5728\u6781\u8584\u5c42\u4e2d\u7684\u9ad8\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6\u3002\u672c\u6559\u5b66\u6a21\u578b\u7814\u7a76\u5728 -100\u00a0kV/cm \u7684\u6052\u5b9a\u7535\u573a\u4e2d\uff0c\u5927\u6c14\u538b\u6c2e\u6c14\u4e2d\u7684\u8d1f\u6d41\u6ce8\u3002\u8fd9\u662f\u4e00\u4e2a\u4e00\u7ef4\u6a21\u578b\uff0c\u63cf\u8ff0\u521d\u59cb\u7535\u5b50\u79cd\u5b50\u5728\u672a\u6270\u52a8\u7535\u573a\u4e2d\u4ece\u7535\u5b50\u751f\u957f\u5230\u6d41\u6ce8\u4f20\u64ad\u7684\u77ac\u6001\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("streamer_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
