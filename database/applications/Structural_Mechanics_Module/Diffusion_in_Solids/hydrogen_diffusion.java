/*
 * hydrogen_diffusion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:04 by COMSOL 6.3.0.290. */
public class hydrogen_diffusion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Diffusion_in_Solids");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ts", "TransportInSolids", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ts", true);

    model.param().set("L", "20[mm]");
    model.param().descr("L", "\u57df\u5927\u5c0f");
    model.param().set("Ld", "10[mm]");
    model.param().descr("Ld", "\u7f3a\u53e3\u957f\u5ea6");
    model.param().set("Hd", "0.4[mm]");
    model.param().descr("Hd", "\u7f3a\u53e3\u9ad8\u5ea6");
    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("D", "2e-9[m^2/s]", "\u6676\u683c\u6269\u6563\u7cfb\u6570");
    model.param("par2").set("N", "1e6[mol/m^3]", "\u683c\u70b9\u6d53\u5ea6");
    model.param("par2").set("OmegaH", "2e-6[m^3/mol]", "\u6c22\u7684\u504f\u6469\u5c14\u4f53\u79ef");
    model.param("par2").set("T0", "293.15[K]", "\u6e29\u5ea6");
    model.param("par2").set("NTrap1", "2[mol/m^3]", "\u9677\u9631 1 \u6d53\u5ea6");
    model.param("par2").set("NTrap2", "1[mol/m^3]", "\u9677\u9631 2 \u6d53\u5ea6");
    model.param("par2").set("Eb1", "10[kJ/mol]", "\u9677\u9631 1 \u7ed3\u5408\u80fd");
    model.param("par2").set("Eb2", "25[kJ/mol]", "\u9677\u9631 2 \u7ed3\u5408\u80fd");
    model.param("par2").set("pH", "13", "\u6c22\u79bb\u5b50\u6d53\u5ea6");
    model.param("par2").set("Vm", "-0.5[V]", "\u91d1\u5c5e\u7535\u4f4d");
    model.param("par2").set("Ve", "-0.025[V]", "\u7535\u89e3\u8d28\u7535\u4f4d");
    model.param("par2").set("Veq", "0[V]", "\u5e73\u8861\u7535\u4f4d");
    model.param("par2")
         .set("K_va", "1e-4[m/s]", "\u9178\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Volmer \u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2")
         .set("K_vb", "1e-8[mol/(m^2*s)]", "\u78b1\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Volmer \u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2")
         .set("K_ha", "1e-10[m/s]", "\u9178\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Heyrovsky \u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2")
         .set("K_hb", "9e-10[mol/(m^2*s)]", "\u78b1\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Heyrovsky \u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2")
         .set("K_t", "1e-6[mol/(m^2*s)]", "\u9178\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Tafel \u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2").set("K_a", "1e5[m/s]", "\u5438\u6536\u6b63\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2").set("K_ar", "9e9[m/s]", "\u5438\u6536\u53cd\u5411\u53cd\u5e94\u901f\u7387");
    model.param("par2")
         .set("alpha_va", "0.48", "\u9178\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Volmer \u6b63\u53cd\u5e94\u7cfb\u6570");
    model.param("par2")
         .set("alpha_vb", "0.48", "\u78b1\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Volmer \u6b63\u53cd\u5e94\u7cfb\u6570");
    model.param("par2")
         .set("alpha_ha", "0.33", "\u9178\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Heyrovsky \u6b63\u53cd\u5e94\u7cfb\u6570");
    model.param("par2")
         .set("alpha_hb", "0.33", "\u78b1\u6027\u7535\u89e3\u8d28\u4e0b\u7684 Heyrovsky \u6b63\u53cd\u5e94\u7cfb\u6570");
    model.param("par2").set("U0", "2e-2[mm]", "\u4e0a\u8fb9\u754c\u7684\u6307\u5b9a\u4f4d\u79fb");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L");
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", "L/2", 0);
    model.component("comp1").geom("geom1").feature("sq1").label("\u91d1\u5c5e\u57df");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Ld", "Hd"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "(L-Hd)/2"});
    model.component("comp1").geom("geom1").feature("r1").label("\u88c2\u9699");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 2, 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "Hd/2");
    model.component("comp1").geom("geom1").feature("fil1").label("\u88c2\u9699\u5706\u89d2");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("fil1");
    model.component("comp1").geom("geom1").feature("dif1").label("\u79fb\u9664\u88c2\u9699");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Iron");
    model.component("comp1").material("mat1").set("family", "iron");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.29");

    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(6);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "U0", 1);
    model.component("comp1").physics("solid").feature("disp1").label("\u6307\u5b9a\u4f4d\u79fb\uff1a\u9876\u90e8");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 8, 9);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1")
         .set("expr", "(NT/N)*exp(Eb/(R_const*T0))/((1+(max(cl,0)/N)*exp(Eb/(R_const*T0)))^2)");
    model.component("comp1").func("an1").set("args", "cl, NT, Eb");
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func("an1").setIndex("argunit", "mol/m^3", 0);
    model.component("comp1").func("an1").setIndex("argunit", "mol/m^3", 1);
    model.component("comp1").func("an1").setIndex("argunit", "kJ/mol", 2);
    model.component("comp1").func("an1").set("funcname", "trapFun");
    model.component("comp1").func("an1").label("\u9677\u9631\u8d21\u732e");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("srcCoeff", "trapFun(c, NTrap1, Eb1)+trapFun(c, NTrap2, Eb2)", "\u6e90\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("J_va", "(1-theta)*(K_va*cHp*exp(-alpha_va*eta)+K_vb*exp(-alpha_vb*eta))", "\u6b63\u5411\u53cd\u5e94\u5bf9\u6d41\u5165\u7684\u8d21\u732e");
    model.component("comp1").variable("var1")
         .set("J_vap", "theta*(K_ha*cHp*exp(-alpha_ha*eta)+K_hb*exp(-alpha_hb*eta)+2*K_t*theta)", "\u53cd\u5411\u53cd\u5e94\u5bf9\u6d41\u5165\u7684\u8d21\u732e");
    model.component("comp1").variable("var1").set("J_influx", "J_va-J_vap", "\u51c0\u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var1")
         .set("cHp", "10^(3-pH)[mol/m^3]", "\u754c\u9762\u5904\u7684 H+ \u6d53\u5ea6");
    model.component("comp1").variable("var1").set("Vo", "Vm-Veq-Ve", "\u8fc7\u7535\u4f4d");
    model.component("comp1").variable("var1").set("eta", "Vo*F_const/(R_const*T0)", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("theta", "c/(K_a/K_ar*(N-c)+c)", "\u52a9\u53d8\u91cf");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 3, 4, 5, 10, 11);
    model.component("comp1").selection("sel1").label("\u6d41\u5165\u8fb9\u754c\u9009\u62e9");

    model.component("comp1").physics("ts").feature("solid1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("ts").create("src1", "Source", 2);
    model.component("comp1").physics("ts").feature("src1").selection().set(1, 2);
    model.component("comp1").physics("ts").feature("src1").setIndex("S", "-srcCoeff*cTIME", 0);
    model.component("comp1").physics("ts").feature("src1").label("\u6e90\uff1a\u6c22\u9677\u9631");
    model.component("comp1").physics("ts").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("ts").feature("fl1").selection().named("sel1");
    model.component("comp1").physics("ts").feature("fl1").setIndex("J0", "J_influx", 0);

    model.component("comp1").multiphysics().create("sas1", "ShrinkageAndSwelling", 2);
    model.component("comp1").multiphysics("sas1").selection().set(1, 2);
    model.component("comp1").multiphysics("sas1").set("OmegaV_c", "OmegaH");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("elemcount", 60);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").set(2);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").set(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "0 10^{range(log10(1/60),1/10,log10(120))} 120");
    model.study("std1").label("\u7814\u7a76 1\uff0c\u8026\u5408");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").label("\u4f20\u8f93\u91cf (ts)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"ts.c"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"ts.tflux_cx", "ts.tflux_cy"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ts", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/sas1", true);
    model.study("std2").label("\u7814\u7a76 2\uff0c\u4e0d\u8026\u5408");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "0 10^{range(log10(1/60),1/10,log10(120))} 120");
    model.study("std2").feature("time").setSolveFor("/multiphysics/sas1", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").label("\u6d41\u4f53\u9759\u5e94\u529b");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u6d41\u4f53\u9759\u5e94\u529b (MPa) \u548c\u5e94\u529b\u9a71\u52a8\u901a\u91cf (mol/m<sup>2</sup>s)");
    model.result("pg1").set("paramindicator", "Time=eval(t,min) min");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "-solid.pm");
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 100);
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"sas1.sflux_cx", "sas1.sflux_cy"});
    model.result("pg1").feature("str1")
         .set("descr", "\u5e94\u529b\u9a71\u52a8\u901a\u91cf \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6c22\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg2").set("paramindicator", "Time=eval(t,min) min");
    model.result("pg2").label("\u6d53\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result("pg2").feature("str1").setIndex("expr", "ts.dflux_cx+sas1.sflux_cx", 0);
    model.result("pg2").feature("str1").setIndex("expr", "ts.dflux_cy+sas1.sflux_cy", 1);
    model.result("pg2").feature("str1").set("color", "black");
    model.result("pg2").feature("str1").set("arrowlength", "normalized");
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6269\u6563\u901a\u91cf\u548c\u5e94\u529b\u8bf1\u5bfc\u901a\u91cf");
    model.result("pg3").set("paramindicator", "Time=eval(t,min) min");
    model.result("pg3").label("\u6269\u6563\u901a\u91cf\u548c\u5e94\u529b\u8bf1\u5bfc\u901a\u91cf");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"sas1.sflux_cx", "v"});
    model.result("pg3").feature("str1").setIndex("expr", "sas1.sflux_cy", 1);
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature().duplicate("str2", "str1");
    model.result("pg3").run();
    model.result("pg3").feature("str2").setIndex("expr", "ts.dflux_cx", 0);
    model.result("pg3").feature("str2").setIndex("expr", "ts.dflux_cy", 1);
    model.result("pg3").feature("str2").set("color", "red");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8ddd\u88c2\u7eb9\u5c16\u7aef\u7684\u8ddd\u79bb (mm)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6c22\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").label("\u91d1\u5c5e\u4e2d\u7684\u6c22\u6d53\u5ea6");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(7);
    model.result("pg4").feature("lngr1").set("expr", "c");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1")
         .setIndex("legends", "\u8003\u8651\u5e94\u529b\u9a71\u52a8\u7684\u6269\u6563", 0);
    model.result("pg4").feature("lngr1").set("linemarker", "cycle");
    model.result("pg4").feature("lngr1").set("markerpos", "interp");
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("data", "dset2");
    model.result("pg4").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg4").feature("lngr2").setIndex("legends", "Without stress-driven diffusion", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6c22\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg5").label("\u88c2\u7eb9\u5c16\u7aef\u7684\u6c22\u6d53\u5ea6");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(7);
    model.result("pg5").feature("ptgr1").set("expr", "c");
    model.result("pg5").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg5").feature("ptgr1").set("markerpos", "interp");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1")
         .setIndex("legends", "\u8003\u8651\u5e94\u529b\u9a71\u52a8\u7684\u6269\u6563", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("data", "dset2");
    model.result("pg5").feature("ptgr2")
         .setIndex("legends", "\u4e0d\u8003\u8651\u5e94\u529b\u9a71\u52a8\u7684\u6269\u6563", 0);
    model.result("pg5").run();

    model.title("\u6c22\u5728\u91d1\u5c5e\u4e2d\u7684\u6269\u6563");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u6c22\u5728\u542b\u6c34\u7535\u89e3\u8d28\u4e2d\u7684\u5438\u6536\u548c\u6269\u6563\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u56fa\u4f53\u4f20\u9012\u201d\u63a5\u53e3\u6765\u6a21\u62df\u6d53\u5ea6\u9a71\u52a8\u548c\u5e94\u529b\u9a71\u52a8\u7684\u6269\u6563\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("hydrogen_diffusion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
