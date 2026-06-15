/*
 * metal_air_surface_plasmon_polariton.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:44 by COMSOL 6.3.0.290. */
public class metal_air_surface_plasmon_polariton {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("ftplistmethod", "manual");
    model.study("std1").feature("bma").set("shiftactive", false);
    model.study("std1").feature("bma").set("linpsolnum", "auto");
    model.study("std1").feature("bma").set("solnum", "auto");
    model.study("std1").feature("bma").set("notsolnum", "auto");
    model.study("std1").feature("bma").set("outputmap", new String[]{});
    model.study("std1").feature("bma").set("ngenAUX", "1");
    model.study("std1").feature("bma").set("goalngenAUX", "1");
    model.study("std1").feature("bma").set("ngenAUX", "1");
    model.study("std1").feature("bma").set("goalngenAUX", "1");
    model.study("std1").feature("bma").setSolveFor("/physics/ewfd", true);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

    model.param().set("L", "2[um]");
    model.param().descr("L", "\u4eff\u771f\u57df\u957f\u5ea6");
    model.param().set("H", "3[um]");
    model.param().descr("H", "\u4eff\u771f\u57df\u9ad8\u5ea6");
    model.param().set("lda0", "400[nm]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H/2", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat1").label("Ag (Silver) (Johnson and Christy 1972: n,k 0.188-1.94 um)");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.1879", "1.07"}, 
         {"0.1916", "1.10"}, 
         {"0.1953", "1.12"}, 
         {"0.1993", "1.14"}, 
         {"0.2033", "1.15"}, 
         {"0.2073", "1.18"}, 
         {"0.2119", "1.20"}, 
         {"0.2164", "1.22"}, 
         {"0.2214", "1.25"}, 
         {"0.2262", "1.26"}, 
         {"0.2313", "1.28"}, 
         {"0.2371", "1.28"}, 
         {"0.2426", "1.30"}, 
         {"0.2490", "1.31"}, 
         {"0.2551", "1.33"}, 
         {"0.2616", "1.35"}, 
         {"0.2689", "1.38"}, 
         {"0.2761", "1.41"}, 
         {"0.2844", "1.41"}, 
         {"0.2924", "1.39"}, 
         {"0.3009", "1.34"}, 
         {"0.3107", "1.13"}, 
         {"0.3204", "0.81"}, 
         {"0.3315", "0.17"}, 
         {"0.3425", "0.14"}, 
         {"0.3542", "0.10"}, 
         {"0.3679", "0.07"}, 
         {"0.3815", "0.05"}, 
         {"0.3974", "0.05"}, 
         {"0.4133", "0.05"}, 
         {"0.4305", "0.04"}, 
         {"0.4509", "0.04"}, 
         {"0.4714", "0.05"}, 
         {"0.4959", "0.05"}, 
         {"0.5209", "0.05"}, 
         {"0.5486", "0.06"}, 
         {"0.5821", "0.05"}, 
         {"0.6168", "0.06"}, 
         {"0.6595", "0.05"}, 
         {"0.7045", "0.04"}, 
         {"0.7560", "0.03"}, 
         {"0.8211", "0.04"}, 
         {"0.8920", "0.04"}, 
         {"0.9840", "0.04"}, 
         {"1.0880", "0.04"}, 
         {"1.2160", "0.09"}, 
         {"1.3930", "0.13"}, 
         {"1.6100", "0.15"}, 
         {"1.9370", "0.24"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.1879", "1.212"}, 
         {"0.1916", "1.232"}, 
         {"0.1953", "1.255"}, 
         {"0.1993", "1.277"}, 
         {"0.2033", "1.296"}, 
         {"0.2073", "1.312"}, 
         {"0.2119", "1.325"}, 
         {"0.2164", "1.336"}, 
         {"0.2214", "1.342"}, 
         {"0.2262", "1.344"}, 
         {"0.2313", "1.357"}, 
         {"0.2371", "1.367"}, 
         {"0.2426", "1.378"}, 
         {"0.2490", "1.389"}, 
         {"0.2551", "1.393"}, 
         {"0.2616", "1.387"}, 
         {"0.2689", "1.372"}, 
         {"0.2761", "1.331"}, 
         {"0.2844", "1.264"}, 
         {"0.2924", "1.161"}, 
         {"0.3009", "0.964"}, 
         {"0.3107", "0.616"}, 
         {"0.3204", "0.392"}, 
         {"0.3315", "0.829"}, 
         {"0.3425", "1.142"}, 
         {"0.3542", "1.419"}, 
         {"0.3679", "1.657"}, 
         {"0.3815", "1.864"}, 
         {"0.3974", "2.070"}, 
         {"0.4133", "2.275"}, 
         {"0.4305", "2.462"}, 
         {"0.4509", "2.657"}, 
         {"0.4714", "2.869"}, 
         {"0.4959", "3.093"}, 
         {"0.5209", "3.324"}, 
         {"0.5486", "3.586"}, 
         {"0.5821", "3.858"}, 
         {"0.6168", "4.152"}, 
         {"0.6595", "4.483"}, 
         {"0.7045", "4.838"}, 
         {"0.7560", "5.242"}, 
         {"0.8211", "5.727"}, 
         {"0.8920", "6.312"}, 
         {"0.9840", "6.992"}, 
         {"1.0880", "7.795"}, 
         {"1.2160", "8.828"}, 
         {"1.3930", "10.10"}, 
         {"1.6100", "11.85"}, 
         {"1.9370", "14.08"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(1, 3);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(6, 7);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Numeric");

    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "5");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature().move("bma1", 1);
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("punit", "nm", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(340,10,600)", 0);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "epsilonr");
    model.component("comp1").func("an1").set("expr", "(mat1.rfi.nr(x)-1i*mat1.rfi.ni(x))^2");
    model.component("comp1").func("an1").set("complex", true);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2")
         .set("hmax", "(lda0/real(sqrt(epsilonr(lda0/1[m])/(1+epsilonr(lda0/1[m])))))/12");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 27, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rport_1", "ewfd.Tport_2", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u7aef\u53e3 1", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 2", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "lda0");
    model.result("pg2").feature("glob1").set("xdataunit", "nm");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewfd)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "root.comp1.ewfd.normEbm_1");
    model.result("pg3").feature("line1").set("colortable", "RainbowLight");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").create("hght1", "Height");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewfd)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "root.comp1.ewfd.normEbm_2");
    model.result("pg4").feature("line1").set("colortable", "RainbowLight");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 4});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg1").feature("arws1").set("xnumber", 50);
    model.result("pg1").feature("arws1").set("ynumber", 50);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "1e-3");
    model.result("pg2").run();
    model.result("pg2").label("\u900f\u5149\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").set("ylabel", "\u900f\u5149\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").remove("unit", new int[]{0, 2});
    model.result("pg2").feature("glob1").remove("descr", new int[]{0, 2});
    model.result("pg2").feature("glob1").remove("expr", new int[]{0, 2});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("SPP \u8272\u6563");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u672c\u4f53\u94f6\u7684 SPP \u8272\u6563");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u4f20\u64ad\u5e38\u6570 (1/m)");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "freq*h_const", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "eV", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u80fd\u91cf", 0);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "ewfd.beta_1");
    model.result("pg5").feature("glob1").set("linestyle", "none");
    model.result("pg5").feature("glob1").set("linewidth", 3);
    model.result("pg5").feature("glob1").set("linemarker", "circle");
    model.result("pg5").feature("glob1").set("legendmethod", "manual");
    model.result("pg5").feature("glob1").setIndex("legends", "\u6a21\u62df\u7684 SPP \u8272\u6563", 0);
    model.result("pg5").feature().duplicate("glob2", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("xdataexpr", "ewfd.k0");
    model.result("pg5").feature("glob2").set("linestyle", "dashed");
    model.result("pg5").feature("glob2").set("linecolor", "black");
    model.result("pg5").feature("glob2").set("linemarker", "none");
    model.result("pg5").feature("glob2").setIndex("legends", "\u5149\u7ebf", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").feature("col1").set("expr", "-real(ewfd.beta_1)/imag(ewfd.beta_1)");
    model.result("pg5").feature("glob1").feature("col1").set("colortable", "Viridis");
    model.result("pg5").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg5").feature("glob1").feature("col1").set("rangecolormin", 0);
    model.result("pg5").feature("glob1").feature("col1").set("rangecolormax", 800);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob3", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob3")
         .set("xdataexpr", "ewfd.omega/c_const*sqrt(epsilonr(lda0)/(epsilonr(lda0)+1))");
    model.result("pg5").feature("glob3").set("linestyle", "solid");
    model.result("pg5").feature("glob3").set("linewidth", 4);
    model.result("pg5").feature("glob3").set("linemarker", "none");
    model.result("pg5").feature("glob3").setIndex("legends", "\u89e3\u6790\u7684 SPP \u8272\u6563", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob3").feature("col1")
         .set("expr", "real((ewfd.omega/c_const)*sqrt(epsilonr(lda0)/(epsilonr(lda0)+1)))/imag(-(ewfd.omega/c_const)*sqrt(epsilonr(lda0)/(epsilonr(lda0)+1)))");
    model.result("pg5").feature("glob3").feature("col1").set("colorlegend", false);
    model.result("pg5").run();

    model
         .title("\u91d1\u5c5e-\u7a7a\u6c14\u8868\u9762\u7b49\u79bb\u6781\u5316\u6fc0\u5143\u4f20\u64ad\u548c\u8272\u6563\u4eff\u771f");

    model
         .description("\u4ec5\u6cbf\u8868\u9762\u4f20\u64ad\u7684\u7535\u78c1\u6ce2\uff0c\u4f8b\u5982\u8868\u9762\u7b49\u79bb\u6781\u5316\u6fc0\u5143 (SPP)\uff0c\u56e0\u5176\u5728\u7eb3\u7c73\u5149\u5b66\u4e2d\u7684\u6f5c\u5728\u5e94\u7528\u5f15\u53d1\u4e86\u5e7f\u6cdb\u7684\u7814\u7a76\u5174\u8da3\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u6cbf\u7a7a\u6c14\u4e0e\u94f6\u672c\u4f53\u4e4b\u95f4\u7684\u754c\u9762\u4f20\u64ad\u7684 SPP \u4ee5\u53ca\u9891\u7387-\u6ce2\u77e2\u8272\u6563\u5173\u7cfb\u3002");

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
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();

    model.label("metal_air_surface_plasmon_polariton.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
