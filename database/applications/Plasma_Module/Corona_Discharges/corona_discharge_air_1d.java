/*
 * corona_discharge_air_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:02 by COMSOL 6.3.0.290. */
public class corona_discharge_air_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Corona_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 10, 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.01, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("mueN", "3.74e24*(plas.Erd*1e21)^-0.22[1/(V*m*s)]");
    model.component("comp1").variable("var1").descr("mueN", "\u7ea6\u5316\u7535\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1").set("muiN", "6e21[1/(V*s*m)]");
    model.component("comp1").variable("var1").descr("muiN", "\u7ea6\u5316\u79bb\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1").set("rnp", "2e-6[cm^3/s]");
    model.component("comp1").variable("var1").descr("rnp", "\u79bb\u5b50-\u79bb\u5b50\u590d\u5408");
    model.component("comp1").variable("var1").set("rei", "5e-8[cm^3/s]");
    model.component("comp1").variable("var1").descr("rei", "\u7535\u5b50-\u79bb\u5b50\u590d\u5408");
    model.component("comp1").variable("var1").set("Vapp", "-V0*ramp");
    model.component("comp1").variable("var1").descr("Vapp", "\u5916\u52a0\u7535\u538b");
    model.component("comp1").variable("var1").set("ramp", "tanh(1e5*t)");
    model.component("comp1").variable("var1").set("p0", "760[torr]");
    model.component("comp1").variable("var1").descr("p0", "\u6c14\u538b");
    model.component("comp1").variable("var1").set("t0", "600[K]");
    model.component("comp1").variable("var1").descr("t0", "\u6c14\u4f53\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("ni0", "1e17[m^-3]");
    model.component("comp1").variable("var1").descr("ni0", "\u521d\u59cb\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("ne0", "1e10[m^-3]");
    model.component("comp1").variable("var1").descr("ne0", "\u521d\u59cb\u7535\u5b50\u6570\u5bc6\u5ea6");

    model.param().set("V0", "45[kV]");

    model.component("comp1").physics("plas").prop("VerticalHeight").set("dz", "1[m]");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").prop("ElectronProperties")
         .set("MeanElectronEnergyModel", "LocalFieldApproximationE");
    model.component("comp1").physics("plas").prop("ShapeProperty").set("Formulation", "FVMConst");
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 1);
    model.component("comp1").physics("plas").feature("eir1").set("formula", "A+e=>p+2e");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir1").set("de", 15);
    model.component("comp1").physics("plas").feature("eir1").set("SpecifyReactionUsing", "UseLookupTable");
    model.component("comp1").physics("plas").feature("eir1").set("RateConstantForm", "UseTownsend");
    model.component("comp1").physics("plas").feature("eir1")
         .set("xtownratedata", new String[]{"0", "1.035", "1.049", "1.061", "1.074", "1.088", "1.105", "1.127", "1.160", "1.212", 
         "1.296", "1.422", "1.605", "1.851", "2.155", "2.518", "2.924", "3.359", "3.804", "4.252", 
         "4.703", "5.167", "5.629", "6.106", "6.595", "7.139", "7.717", "8.347", "9.043", "9.804", 
         "10.65", "11.59", "12.64", "13.80", "15.11", "16.57"});
    model.component("comp1").physics("plas").feature("eir1")
         .set("ytownratedata", new String[]{"0", "0.000", "0.7818E-32", "0.2129E-30", "0.2556E-29", "0.2353E-28", "0.1758E-27", "0.1040E-26", "0.5210E-26", "0.2197E-25", 
         "0.8170E-25", "0.2653E-24", "0.7771E-24", "0.2057E-23", "0.4991E-23", "0.1116E-22", "0.2320E-22", "0.4511E-22", "0.8270E-22", "0.1440E-21", 
         "0.2387E-21", "0.3801E-21", "0.5831E-21", "0.8652E-21", "0.1246E-20", "0.1747E-20", "0.2390E-20", "0.3193E-20", "0.4177E-20", "0.5356E-20", 
         "0.6741E-20", "0.8337E-20", "0.1015E-19", "0.1217E-19", "0.1439E-19", "0.1679E-19"});
    model.component("comp1").physics("plas").create("eir2", "ElectronImpactReaction", 1);
    model.component("comp1").physics("plas").feature("eir2").set("formula", "A+e=>n");
    model.component("comp1").physics("plas").feature("eir2").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir2").set("SpecifyReactionUsing", "UseLookupTable");
    model.component("comp1").physics("plas").feature("eir2").set("RateConstantForm", "UseTownsend");
    model.component("comp1").physics("plas").feature("eir2")
         .set("xtownratedata", new String[]{"0", "0.4026E-01", "0.4571E-01", "0.5210E-01", "0.5954E-01", "0.6805E-01", "0.7750E-01", "0.8764E-01", "0.9806E-01", "0.1084", 
         "0.1184", "0.1281", "0.1374", "0.1465", "0.1556", "0.1647", "0.1742", "0.1842", "0.1946", "0.2057", 
         "0.2178", "0.2310", "0.2458", "0.2628", "0.2826", "0.3062", "0.3343", "0.3675", "0.4068", "0.4514", 
         "0.5005", "0.5525", "0.6054", "0.6576", "0.7075", "0.7540", "0.7969", "0.8352", "0.8693", "0.8996", 
         "0.9264", "0.9497", "0.9706", "0.9894", "1.006", "1.022", "1.035", "1.049", "1.061", "1.074", 
         "1.088", "1.105", "1.127", "1.160", "1.212", "1.296", "1.422", "1.605", "1.851", "2.155", 
         "2.518", "2.924", "3.359", "3.804", "4.252", "4.703", "5.167", "5.629", "6.106", "6.595", 
         "7.139", "7.717", "8.347", "9.043", "9.804", "10.65", "11.59", "12.64", "13.80", "15.11", 
         "16.57"});
    model.component("comp1").physics("plas").feature("eir2")
         .set("ytownratedata", new String[]{"0", "0.1273E-39", "0.1621E-39", "0.1964E-39", "0.2269E-39", "0.2507E-39", "0.2660E-39", "0.2722E-39", "0.2700E-39", "0.2611E-39", 
         "0.2473E-39", "0.2303E-39", "0.2119E-39", "0.1930E-39", "0.1746E-39", "0.1571E-39", "0.1407E-39", "0.1256E-39", "0.1118E-39", "0.9925E-40", 
         "0.8797E-40", "0.7782E-40", "0.6871E-40", "0.6055E-40", "0.5326E-40", "0.4675E-40", "0.4094E-40", "0.3578E-40", "0.3117E-40", "0.2709E-40", 
         "0.2348E-40", "0.2032E-40", "0.1756E-40", "0.1518E-40", "0.1313E-40", "0.1138E-40", "0.9890E-41", "0.8624E-41", "0.7548E-41", "0.6631E-41", 
         "0.5847E-41", "0.5174E-41", "0.4591E-41", "0.1439E-30", "0.2774E-29", "0.2915E-28", "0.2091E-27", "0.1256E-26", "0.6007E-26", "0.2325E-25", 
         "0.7685E-25", "0.2237E-24", "0.5625E-24", "0.1275E-23", "0.2575E-23", "0.4780E-23", "0.8015E-23", "0.1242E-22", "0.1772E-22", "0.2351E-22", 
         "0.2917E-22", "0.3414E-22", "0.3795E-22", "0.4045E-22", "0.4166E-22", "0.4164E-22", "0.4071E-22", "0.3907E-22", "0.3694E-22", "0.3450E-22", 
         "0.3193E-22", "0.2932E-22", "0.2675E-22", "0.2428E-22", "0.2195E-22", "0.1978E-22", "0.1779E-22", "0.1597E-22", "0.1433E-22", "0.1286E-22", 
         "0.1155E-22"});
    model.component("comp1").physics("plas").create("eir3", "ElectronImpactReaction", 1);
    model.component("comp1").physics("plas").feature("eir3").set("formula", "A + A + e => n + A");
    model.component("comp1").physics("plas").feature("eir3").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir3")
         .set("kf", "1.4e-41*(0.026/plas.Te)*exp(100/t0-0.061/plas.Te)*N_A_const^2*0.1");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 1);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "e+p=>A");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "rei*N_A_const");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 1);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "n+p=>A+A");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", "rnp*N_A_const");
    model.component("comp1").physics("plas").feature("A").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("A").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("p").set("sType", "ion");
    model.component("comp1").physics("plas").feature("p").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("p").set("z", 1);
    model.component("comp1").physics("plas").feature("p").set("n0", "ni0");
    model.component("comp1").physics("plas").feature("p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("plas").feature("p").set("um", "muiN/plas.Nn");
    model.component("comp1").physics("plas").feature("n").set("sType", "ion");
    model.component("comp1").physics("plas").feature("n").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("n").set("z", -1);
    model.component("comp1").physics("plas").feature("n").set("n0", "ni0");
    model.component("comp1").physics("plas").feature("n")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("plas").feature("n").set("um", "muiN/plas.Nn");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "p=>A");
    model.component("comp1").physics("plas").feature("sr1").selection().set(1);
    model.component("comp1").physics("plas").feature("sr1").set("gammai", 0.05);
    model.component("comp1").physics("plas").feature("sr1").set("ebari", 4);
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "p=>A");
    model.component("comp1").physics("plas").feature("sr2").selection().set(2);
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr3").set("formula", "n=>A");
    model.component("comp1").physics("plas").feature("sr3").selection().all();
    model.component("comp1").physics("plas").feature("pes1").set("T", "t0");
    model.component("comp1").physics("plas").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"mueN", "0", "0", "0", "mueN", "0", "0", "0", "mueN"});
    model.component("comp1").physics("plas").feature("pes1").set("SpecifyMeanElectronEnergy", "MeanEnergyTable");
    model.component("comp1").physics("plas").feature("pes1")
         .set("enrgXdata", new String[]{"0", "0.1000", "0.1116", "0.1246", "0.1391", "0.1553", "0.1734", "0.1936", "0.2162", "0.2413", 
         "0.2694", "0.3008", "0.3358", "0.3749", "0.4185", "0.4672", "0.5216", "0.5824", "0.6502", "0.7258", 
         "0.8103", "0.9047", "1.010", "1.128", "1.259", "1.405", "1.569", "1.752", "1.956", "2.183", 
         "2.437", "2.721", "3.038", "3.391", "3.786", "4.227", "4.719", "5.269", "5.882", "6.567", 
         "7.331", "8.184", "9.137", "10.20", "11.39", "12.71", "14.19", "15.85", "17.69", "19.75", 
         "22.05", "24.62", "27.48", "30.68", "34.25", "38.24", "42.69", "47.66", "53.21", "59.41", 
         "66.32", "74.04", "82.66", "92.29", "103.0", "115.0", "128.4", "143.4", "160.0", "178.7", 
         "199.5", "222.7", "248.6", "277.6", "309.9", "346.0", "386.2", "431.2", "481.4", "537.4", 
         "600.0", "627.3", "704.9", "792.0", "890.0", "1000."});
    model.component("comp1").physics("plas").feature("pes1")
         .set("enrgYdata", new String[]{"0", "0.4026E-01", "0.4538E-01", "0.5134E-01", "0.5823E-01", "0.6607E-01", "0.7480E-01", "0.8421E-01", "0.9400E-01", "0.1038", 
         "0.1135", "0.1228", "0.1317", "0.1404", "0.1490", "0.1576", "0.1663", "0.1753", "0.1848", "0.1946", 
         "0.2051", "0.2164", "0.2287", "0.2424", "0.2579", "0.2757", "0.2966", "0.3212", "0.3504", "0.3844", 
         "0.4236", "0.4675", "0.5149", "0.5644", "0.6144", "0.6635", "0.7103", "0.7542", "0.7947", "0.8312", 
         "0.8640", "0.8933", "0.9194", "0.9423", "0.9629", "0.9811", "0.9980", "1.013", "1.027", "1.040", 
         "1.052", "1.064", "1.076", "1.090", "1.106", "1.127", "1.158", "1.206", "1.280", "1.390", 
         "1.550", "1.763", "2.029", "2.353", "2.719", "3.117", "3.534", "3.956", "4.380", "4.817", 
         "5.246", "5.684", "6.136", "6.598", "7.112", "7.655", "8.242", "8.889", "9.593", "10.36", 
         "11.22", "11.59", "12.64", "13.80", "15.11", "16.57"});
    model.component("comp1").physics("plas").feature("init1").set("neinit", "ne0");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 0);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(2);
    model.component("comp1").physics("plas").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("plas").feature("mct1").selection().set(1);
    model.component("comp1").physics("plas").feature("mct1").set("V0", "Vapp");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("plas").feature("wall1").selection().all();

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 10^{range(-8,8/49,0)}");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"plas.ne"});

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "r");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"V"});
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u5b50\u548c\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0.009);
    model.result("pg1").set("xmax", 11);
    model.result("pg1").set("ymin", "1e9");
    model.result("pg1").set("ymax", "1e17");
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("ylog", true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").label("\u7535\u5b50");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoplotlabel", true);
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").label("\u6b63\u79bb\u5b50");
    model.result("pg1").feature("lngr2").set("expr", "plas.n_wp");
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").label("\u8d1f\u79bb\u5b50");
    model.result("pg1").feature("lngr3").set("expr", "plas.n_wn");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlog", true);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "label");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7ea6\u5316\u7535\u573a");
    model.result("pg4").set("xlog", true);
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").set("expr", "plas.Erd");
    model.result("pg4").feature("lngr1").set("unit", "Td");
    model.result("pg4").run();
    model.result().dataset().create("rev1", "Revolve1D");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u5b50\u5bc6\u5ea6\u5bf9\u6570");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "log10(plas.ne)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u8d1f\u79bb\u5b50\u5bc6\u5ea6\u5bf9\u6570");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "log10(plas.n_wn)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6b63\u79bb\u5b50\u5bc6\u5ea6\u5bf9\u6570");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "log10(plas.n_wp)");
    model.result("pg7").run();
    model.result("pg7").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "V0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "V0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("plistarr", "20 25 30 35 40 45 50", 0);
    model.study("std1").feature("param").setIndex("punit", "kV", 0);
    model.study("std1").feature("time").set("plot", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").label("\u7535\u6d41 Vs. \u7535\u538b");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(2);
    model.result("pg8").feature("ptgr1").set("expr", "plas.nJt");
    model.result("pg8").feature("ptgr1").set("descr", "\u58c1\u4e0a\u603b\u79bb\u5b50\u6d41\u5bc6\u5ea6");
    model.result("pg8").feature("ptgr1").set("expr", "abs(plas.nJt)");
    model.result("pg8").feature("ptgr1").set("unit", "mA/m^2");
    model.result("pg8").feature("ptgr1").set("descractive", true);
    model.result("pg8").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "V0");
    model.result("pg8").feature("ptgr1").set("xdataunit", "kV");
    model.result("pg8").feature("ptgr1").set("xdatadescractive", true);
    model.result("pg8").feature("ptgr1").set("xdatadescr", "\u5916\u52a0\u7535\u538b");
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u603b\u79bb\u5b50\u6d41\u5bc6\u5ea6 (mA/m<sup>2</sup>)");
    model.result("pg8").run();

    model.title("\u7a7a\u6c14\u4e2d\u7684\u5927\u6c14\u538b\u7535\u6655\u653e\u7535");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u7814\u7a76\u5927\u6c14\u538b\u4e0b\u5e72\u7a7a\u6c14\u4e2d\u7684\u540c\u8f74\u76f4\u6d41\u7535\u6655\u653e\u7535\u73b0\u8c61\uff0c\u6a21\u578b\u7684\u5c3a\u5bf8\u548c\u5de5\u4f5c\u6761\u4ef6\u4e0e\u91c7\u7528\u7ebf-\u677f\u914d\u7f6e\u7684\u9759\u7535\u9664\u5c18\u5668\u76f8\u4f3c\uff0c\u5176\u4e2d\u5185\u7ebf\u7535\u6781\u7684\u534a\u5f84\u4e3a 100 mm\uff0c\u7535\u6781\u4e4b\u95f4\u7684\u95f4\u9699\u4e3a 10\u00a0cm\u3002\n\n\u6a21\u578b\u91c7\u7528\u6f02\u79fb-\u6269\u6563\u8fd1\u4f3c\u7684\u65b9\u6cd5\u6c42\u89e3\u7535\u5b50\u548c\u79bb\u5b50\u7684\u8fde\u7eed\u6027\u4e0e\u52a8\u91cf\u65b9\u7a0b\uff0c\u5e76\u4e0e\u6cca\u677e\u65b9\u7a0b\u81ea\u6d3d\u8026\u5408\u3002\u6a21\u578b\u4e2d\u91c7\u7528\u4e86\u5c40\u90e8\u573a\u8fd1\u4f3c\uff0c\u5373\u5047\u8bbe\u4f20\u9012\u7cfb\u6570\u548c\u6e90\u7cfb\u6570\u53ef\u901a\u8fc7\u7ea6\u5316\u7535\u573a\u5145\u5206\u53c2\u6570\u5316\u3002\n\n\u672c\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u7a33\u5b9a\u6301\u7eed\u653e\u7535\u8fc7\u7a0b\uff0c\u5176\u4e2d\u5bf9\u5185\u7535\u6781\u65bd\u52a0\u6570\u5341\u5343\u4f0f\u7684\u7535\u538b\uff0c\u5e76\u4f7f\u5916\u7535\u6781\u63a5\u5730\u3002\u91cd\u70b9\u5206\u6790\u5e26\u7535\u7c92\u5b50\u7684\u4ea7\u751f\u548c\u4f20\u8f93\uff0c\u4ee5\u53ca\u8fd9\u4e9b\u8fc7\u7a0b\u5982\u4f55\u8f6c\u5316\u4e3a\u653e\u7535\u7684\u7535\u6d41-\u7535\u538b\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("corona_discharge_air_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
