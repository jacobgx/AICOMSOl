/*
 * buckling_hdpe_liner.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:38 by COMSOL 6.3.0.290. */
public class buckling_hdpe_liner {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().label("\u6750\u6599\u5c5e\u6027");
    model.param().set("mu", "9.25[MPa]");
    model.param().descr("mu", "\u526a\u5207\u6a21\u91cf");
    model.param().set("beta1", "20");
    model.param().descr("beta1", "\u80fd\u91cf\u56e0\u5b50\uff0c\u7f51\u7edc 1");
    model.param().set("beta2_i", "23.7");
    model.param().descr("beta2_i", "\u521d\u59cb\u80fd\u91cf\u56e0\u5b50\uff0c\u7f51\u7edc 2");
    model.param().set("beta2_f", "11.2");
    model.param().descr("beta2_f", "\u6700\u7ec8\u80fd\u91cf\u56e0\u5b50\uff0c\u7f51\u7edc 2");
    model.param().set("alpha", "30");
    model.param().descr("alpha", "\u80fd\u91cf\u56e0\u5b50\u6f14\u5316\u7cfb\u6570");
    model.param().set("thetar", "-94[K]");
    model.param().descr("thetar", "\u521a\u5ea6\u6e29\u5ea6\u54cd\u5e94");
    model.param().set("m", "117.2");
    model.param().descr("m", "\u6e29\u5ea6\u6307\u6570");
    model.param().set("theta0", "273.15[K]");
    model.param().descr("theta0", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("T", "293.15[K]");
    model.param().descr("T", "\u6e29\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570");
    model.param("par2").set("L", "1[m]");
    model.param("par2").descr("L", "\u7ba1\u9053\u7684\u957f\u5ea6");
    model.param("par2").set("outer_r", "114[mm]/2");
    model.param("par2").descr("outer_r", "\u5185\u886c\u7684\u5916\u534a\u5f84");
    model.param("par2").set("th_liner", "6.2[mm]");
    model.param("par2").descr("th_liner", "\u5185\u886c\u7684\u539a\u5ea6");
    model.param("par2").set("th_pipe", "5[mm]");
    model.param("par2").descr("th_pipe", "\u7ba1\u9053\u7684\u539a\u5ea6");
    model.param().create("par3");
    model.param("par3").label("\u6c14\u4f53\u5c5e\u6027");
    model.param("par3").set("M", "16.04[g/mol]");
    model.param("par3").descr("M", "\u6469\u5c14\u8d28\u91cf");
    model.param("par3").set("Rs", "R_const/M");
    model.param("par3").descr("Rs", "\u5355\u4f4d\u8d28\u91cf\u7684\u6c14\u4f53\u5e38\u6570");
    model.param("par3").set("mdot", "750[cm^3/min]*0.657[kg/m^3]");
    model.param("par3").descr("mdot", "\u8d28\u91cf\u6d41\u7387");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "outer_r");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th_liner", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new String[]{"outer_r", "outer_r*0.996"});
    model.component("comp1").geom("geom1").feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("e1").setIndex("layer", "th_liner", 0);
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 1, 2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("e1", 2, 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "outer_r+th_pipe");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("rot", 90);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "th_pipe", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("c2", 2);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("del1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(6, 7);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u7ba1\u9053\u5185\u8868\u9762");
    model.component("comp1").selection("sel1").set(6, 7);

    model.component("comp1").pair("p1").source().named("sel1");
    model.component("comp1").pair("p1").destination().set(11, 12);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u5185\u886c\u5916\u8868\u9762");
    model.component("comp1").selection("sel2").set(11, 12);

    model.component("comp1").pair("p1").destination().named("sel2");

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(13, 14);
    model.component("comp1").selection("sel3").label("\u5185\u886c\u5185\u8868\u9762");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection("uni1").label("\u7ba1\u9053\u2013\u5185\u886c\u8154\u4f53");

    model.component("comp1").physics("solid").selection().set(3, 4);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u5185\u886c");
    model.component("comp1").selection("sel4").set(3, 4);

    model.component("comp1").physics("solid").selection().named("sel4");
    model.component("comp1").physics("solid").prop("d").set("d", "L");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", 8);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(9, 10);
    model.component("comp1").physics("solid").create("enc1", "EnclosedCavity", 1);
    model.component("comp1").physics("solid").feature("enc1").label("\u5c01\u95ed\u8154\uff0c\u5185\u538b");
    model.component("comp1").physics("solid").feature("enc1").selection().named("sel3");
    model.component("comp1").physics("solid").feature("enc1").set("volumeType", "openSurface");
    model.component("comp1").physics("solid").feature("enc1").selection("referencePoint").set(11);
    model.component("comp1").physics("solid").feature("enc1").set("fV", 2);
    model.component("comp1").physics("solid").feature("enc1").feature().remove("fl1");
    model.component("comp1").physics("solid").feature("enc1").create("pp1", "PrescribedPressure", 1);
    model.component("comp1").physics("solid").feature("enc1").feature("pp1").set("p", "1[atm]");
    model.component("comp1").physics("solid").feature().duplicate("enc2", "enc1");
    model.component("comp1").physics("solid").feature("enc2")
         .label("\u5c01\u95ed\u8154\u4f53\uff0c\u5916\u90e8\u538b\u529b");
    model.component("comp1").physics("solid").feature("enc2").selection().named("uni1");
    model.component("comp1").physics("solid").feature("enc2").set("allowExternalSelection", true);
    model.component("comp1").physics("solid").feature("enc2").feature("pp1").set("p", "Rs*mdot*t*T/solid.enc2.V");
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().named("sel4");
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "ArrudaBoyce");
    model.component("comp1").physics("solid").feature("hmm1")
         .set("Compressibility_ArrudaBoyce", "CompressibleUncoupled");
    model.component("comp1").physics("solid").feature("hmm1").create("pvp1", "PolymerViscoplasticity", 2);
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1")
         .set("ViscoplasticityModel", "BergstromBischoff");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav1", "beta1");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav2_i", "beta2_i");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("betav2_f", "beta2_f");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("alpha", "alpha");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("thermalEffects", "powerLaw");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("m", "m");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("minput_temperature", "T");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("T0", "theta0");
    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("timeMethod", "ode");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("sel4");
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", new String[]{"2[GPa]"});
    model.component("comp1").material("mat1").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce").set("Nseg", new String[]{"4.5"});
    model.component("comp1").material("mat1").propertyGroup("ArrudaBoyce")
         .set("mu0", new String[]{"mu*(1+(T-theta0)/thetar)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"950[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("BergstromBischoff", "BergstromBischoff", "Bergstrom-Bischoff_viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("A_BeBi", new String[]{"sqrt(2/3)[1/s]"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("sigRes1_BeBi", new String[]{"7.1[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff").set("n1_BeBi", new String[]{"13"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("a1_BeBi", new String[]{"0.183"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("sigRes2_BeBi", new String[]{"32.2[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("n2_BeBi", new String[]{"22.4"});
    model.component("comp1").material("mat1").propertyGroup("BergstromBischoff")
         .set("a2_BeBi", new String[]{"0.183"});
    model.component("comp1").material("mat1").label("HDPE");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 25);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run("map2");

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 0);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/enc2/pp1"});

    model.component("comp1").physics("solid").feature("hmm1").feature("pvp1").set("StaticResponse", "Instantaneous");

    model.study("std1").feature("time").set("tlist", "range(0,5,20[min])");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").feature("comp1_solid_hmm1_pvp1_evp1").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_solid_hmm1_pvp1_evp2").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_solid_hmm1_pvp1_evpe1").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_solid_hmm1_pvp1_evpe2").set("scalemethod", "manual");
    model.sol("sol1").feature("t1").set("timemethod", "bdf");
    model.sol("sol1").feature("t1").feature("fc1").set("minstep", 0.25);
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 20);
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1")
         .setIndex("stopcondarr", "comp1.solid.enc1.A/comp1.solid.enc1.A_ref<0.6", 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "278.15 298.15 323.15", 0);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").getInitialValue();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4f4d\u79fb [mm]");
    model.result("pg1").set("paramindicator", "Time=eval(t) s, T=eval(T) K");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").label("\u5185\u886c");
    model.result("pg1").feature("surf1").set("expr", "solid.disp");
    model.result("pg1").feature("surf1").set("unit", "mm");
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").label("\u7ba1\u9053");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().named("sel1");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result().dataset("mir1").set("data", "dset3");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new String[]{"last", "1"});
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset3");
    model.result("pg2").label("\u6c14\u538b");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6c14\u538b");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u538b\u529b (atm)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"solid.enc2.pp1.p_rel"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u538b\u529b"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"Pa"});
    model.result("pg2").feature("glob1").setIndex("unit", "atm", 0);
    model.result("pg2").feature("glob1").set("linestyle", "dashed");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("linemarker", "asterisk");
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "mir1");
    model.result().dataset("extr1").set("zmax", "20[cm]");
    model.result().dataset("extr1").set("planemap", "yz");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4e09\u7ef4\u7ba1\u9053\u548c\u5185\u886c");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("paramindicator", "Time=eval(t) s, T=eval(T) K");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "1");
    model.result("pg3").feature("vol1").set("coloring", "uniform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("vol1").feature("mtrl1").set("family", "rubber");
    model.result("pg3").feature("vol1").feature("mtrl1").set("color", "gray");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").label("\u5185\u886c");
    model.result("pg3").feature("vol1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg3").feature("vol1").feature("def1").set("expr", new String[]{"0", "u", "v"});
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").selection().geom("geom1", 2);
    model.result().dataset("mir2").selection().geom("geom1", 2);
    model.result().dataset("mir2").selection().set(1, 2);
    model.result().dataset().duplicate("extr2", "extr1");
    model.result().dataset("extr2").set("data", "mir2");
    model.result().dataset("extr2").set("zmin", "5");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u7ba1\u9053");
    model.result("pg3").feature("surf1").set("data", "extr2");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("surf1").feature("mtrl1").set("family", "steelscratched");

    model.view("view3").set("showgrid", false);
    model.view("view3").set("ssao", true);

    model.result("pg3").run();
    model.result("pg3").run();

    model.title("HDPE \u5185\u886c\u7684\u5c48\u66f2");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 Bergstrom-Bischoff \u6750\u6599\u6a21\u578b\u6765\u6a21\u62df\u9ad8\u5bc6\u5ea6\u805a\u4e59\u70ef (HDPE) \u7684\u6e29\u5ea6\u548c\u5e94\u53d8\u76f8\u5173\u7279\u6027\uff0c\u4f8b\u5982\u7528\u4e8e\u5236\u9020\u77f3\u6cb9\u548c\u5929\u7136\u6c14\u5e94\u7528\u4e2d\u53d7\u635f\u7ba1\u9053\u7684\u5185\u886c\uff0c\u6216\u5236\u9020 IV \u578b\u50a8\u6c22\u5bb9\u5668\u7528\u4e8e\u71c3\u6599\u7535\u6c60\u7b49\u3002\u5728\u8fd9\u4e24\u79cd\u60c5\u51b5\u4e0b\uff0c\u7531\u4e8e\u6c14\u4f53\u5728\u5185\u886c\u4e0e\u4e3b\u4f53\u7ed3\u6784\u4e4b\u95f4\u8fdb\u884c\u6e17\u900f\uff0c\u5185\u886c\u5f88\u5bb9\u6613\u53d1\u751f\u584c\u9677\u3002\u8be5\u6a21\u578b\u80fd\u591f\u9884\u6d4b\u4e0d\u540c\u5de5\u4f5c\u6e29\u5ea6\u4e0b\u7684\u584c\u9677\u538b\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("buckling_hdpe_liner.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
