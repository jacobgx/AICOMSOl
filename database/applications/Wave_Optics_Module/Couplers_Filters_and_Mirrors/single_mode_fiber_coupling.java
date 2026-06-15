/*
 * single_mode_fiber_coupling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:37 by COMSOL 6.3.0.290. */
public class single_mode_fiber_coupling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

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
    model.study("std1").feature("bma").setSolveFor("/physics/ewbe", true);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("h_clad", "40[um]", "\u5305\u5c42\u5bbd\u5ea6");
    model.param().set("h_core", "4[um]", "\u82af\u5bbd\u5ea6");
    model.param().set("l_fiber", "100[um]", "\u7ea4\u7ef4\u957f\u5ea6");
    model.param().set("f_lens", "50[um]", "\u7126\u8ddd");
    model.param().set("t_lens", "15[um]", "\u900f\u955c\u539a\u5ea6");
    model.param().set("df", "-4[um]", "\u7126\u70b9\u8c03\u6574");
    model.param().set("t_PML", "1[um]", "\u5b8c\u7f8e\u5339\u914d\u5c42\u539a\u5ea6");
    model.param().set("n_lens", "1.5", "\u6298\u5c04\u7387\uff0c\u900f\u955c");
    model.param().set("n_clad", "1.5", "\u6298\u5c04\u7387\uff0c\u5305\u5c42");
    model.param().set("n_core", "1.51", "\u6298\u5c04\u7387\uff0c\u82af\u5c42");
    model.param().set("lda0", "1[um]", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("l_dom", "f_lens*2", "\u8026\u5408\u957f\u5ea6\u7684\u4e00\u534a");
    model.param().set("lamc", "2*pi*h_core/2/2.405*sqrt(n_core^2-n_clad^2)", "\u5355\u6a21\u4e34\u754c\u6ce2\u957f");
    model.param().set("V", "2*pi/lda0*h_core/2*sqrt(n_core^2-n_clad^2)", "\u5149\u7ea4\u53c2\u6570");
    model.param().set("r_lens", "(n_lens-1)*f_lens", "\u900f\u955c\u66f2\u7387\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"l_fiber", "h_core"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-l_fiber/2-l_dom", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"l_fiber", "h_clad"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"l_dom", "h_clad"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-l_dom", "-h_clad/2"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("r3").set("layertop", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_lens");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"r_lens", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4")
         .set("size", new String[]{"t_lens-(r_lens-sqrt(r_lens^2-h_clad^2/4))", "1"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "h_clad", 1);
    model.component("comp1").geom("geom1").feature("r4")
         .set("pos", new String[]{"r_lens-sqrt(r_lens^2-h_clad^2/4)", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "-h_clad/2", 1);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "r4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 3);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "-t_lens/2+f_lens+df-l_dom+t_lens");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("mov1", "r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"t_PML", "h_clad"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"l_dom+l_fiber-t_PML", "0"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("pos", "-h_clad/2", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u900f\u955c");
    model.component("comp1").material("mat2").selection().set(9, 10, 11, 18, 19, 20);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_lens"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u82af\u5c42");
    model.component("comp1").material("mat3").selection().set(3, 26, 31);
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5305\u5c42");
    model.component("comp1").material("mat4").selection().set(1, 2, 4, 5, 24, 25, 27, 28, 29, 30, 32, 33);
    model.component("comp1").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").set("n", new String[]{"n_clad"});

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "psi");
    model.component("comp1").physics("ewbe").create("port1", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port1").selection().set(3, 5, 7);
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").create("port2", "Port", 1);
    model.component("comp1").physics("ewbe").feature("port2").selection().set(63, 65, 67);
    model.component("comp1").physics("ewbe").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewbe").feature("port2").set("PortSlit", true);
    model.component("comp1").physics("ewbe").feature("port2").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewbe").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("trans1").selection()
         .set(12, 14, 16, 17, 18, 50, 52, 54, 56, 58);
    model.component("comp1").physics("ewbe").feature("trans1").set("n_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("n", "sqrt(ewbe.neff_1)");
    model.component("comp1").physics("ewbe").feature("trans1").set("ki_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("d", "lda0/4/sqrt(ewbe.neff_1)");
    model.component("comp1").physics("ewbe").create("trans2", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("trans2").selection()
         .set(21, 23, 25, 43, 45, 47, 77, 78, 79, 80, 81, 82, 83, 84);
    model.component("comp1").physics("ewbe").feature("trans2").set("n_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans2").set("n", "sqrt(n_lens)");
    model.component("comp1").physics("ewbe").feature("trans2").set("ki_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans2").set("d", "lda0/4/sqrt(n_lens)");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1, 2, 3, 4, 5, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33);
    model.component("comp1").variable("var1").set("psi", "ewbe.beta_1*x");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection()
         .set(6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    model.component("comp1").variable("var2").set("psi", "ewbe.k*x");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection()
         .set(1, 5, 6, 8, 9, 11, 12, 13, 15, 17, 19, 20, 21, 23, 24, 28, 29, 30, 31, 32, 33);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("distr1").label("\u5206\u5e03 - \u7a7a\u95f4");
    model.component("comp1").mesh("mesh1").feature("distr1").selection().set(13, 28, 33, 44);
    model.component("comp1").mesh("mesh1").feature("distr1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("distr2").label("\u5206\u5e03 - \u82af\u5c42");
    model.component("comp1").mesh("mesh1").feature("distr2").selection().set(5, 74);
    model.component("comp1").mesh("mesh1").feature("distr2").set("numelem", 22);
    model.component("comp1").mesh("mesh1").feature("distr3").label("\u5206\u5e03 - \u5305\u5c42");
    model.component("comp1").mesh("mesh1").feature("distr3").selection().set(3, 7, 73, 75);
    model.component("comp1").mesh("mesh1").feature("distr3").set("numelem", 80);
    model.component("comp1").mesh("mesh1").feature("distr4").label("\u5206\u5e03 - PML");
    model.component("comp1").mesh("mesh1").feature("distr4").selection().set(1, 9, 61, 64, 69, 70);
    model.component("comp1").mesh("mesh1").feature("distr4").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("distr5").label("\u5206\u5e03 - \u8f93\u5165\u5149\u7ea4");
    model.component("comp1").mesh("mesh1").feature("distr5").selection().set(2);
    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").label("\u5206\u5e03 - \u8f93\u51fa\u5149\u7ea4");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(51);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature().move("dis1", 6);
    model.component("comp1").mesh("mesh1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis2").label("\u5206\u5e03 - \u900f\u955c");
    model.component("comp1").mesh("mesh1").feature("dis2").selection().set(22, 41);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature().move("bma1", 1);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "h_clad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "h_clad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "df", 0);
    model.study("std1").feature("param").setIndex("plistarr", "-25 -20 -15 range(-10,2,10) 15 20 25", 0);
    model.study("std1").feature("param").setIndex("punit", "um", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewbe)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 17, 1);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewbe.Rport_1", "ewbe.Tport_2", "ewbe.RTtotal", "ewbe.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u7aef\u53e3 1", "\u900f\u5c04\u7387\uff0c\u7aef\u53e3 2", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewbe)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "df");
    model.result("pg2").feature("glob1").set("xdataunit", "um");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewbe)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").selection().set(3, 5, 7);
    model.result("pg3").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_1");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_1)");
    model.result("pg3").feature("lngr1").set("legendexprprecision", 5);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewbe)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").selection().set(63, 65, 67);
    model.result("pg4").feature("lngr1").set("expr", "root.comp1.ewbe.normEbm_2");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1")
         .set("legendpattern", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewbe.neff_2)");
    model.result("pg4").feature("lngr1").set("legendexprprecision", 5);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017 (ewbe)");
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u635f\u8017 (1)");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").remove("unit", 2);
    model.result("pg2").feature("glob1").remove("descr", 2);
    model.result("pg2").feature("glob1").remove("expr", new int[]{2});
    model.result("pg2").feature("glob1").setIndex("descr", "\u635f\u8017", 2);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u5355\u6a21\u5149\u7ea4\u5230\u5149\u7ea4\u8026\u5408");

    model
         .description("\u5c06\u6fc0\u5149\u675f\u805a\u7126\u5230\u5355\u6a21\u5149\u7ea4\u7684\u5c16\u7aef\u662f\u4e00\u79cd\u5e38\u89c1\u7684\u5149\u8026\u5408\u65b9\u6cd5\u3002\u4e3a\u4e86\u83b7\u5f97\u826f\u597d\u7684\u8026\u5408\u6548\u7387\uff0c\u5149\u573a\u7684\u7a7a\u95f4\u6a21\u5f0f\u5fc5\u987b\u4e0e\u5149\u7ea4\u7684\u7a7a\u95f4\u6a21\u5f0f\u76f8\u5339\u914d\u3002\n\n\u5728\u6b64\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u7269\u7406\u573a\u63a5\u53e3\u6765\u8ba1\u7b97\u4e24\u4e2a\u5c0f\u5149\u7ea4\u7aef\u4e4b\u95f4\u7684\u81ea\u7531\u7a7a\u95f4\u8026\u5408\u3002\u8026\u5408\u6548\u7387\u53d6\u51b3\u4e8e\u8026\u5408\u900f\u955c\u7684\u4f4d\u7f6e\u3002");

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

    model.label("single_mode_fiber_coupling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
