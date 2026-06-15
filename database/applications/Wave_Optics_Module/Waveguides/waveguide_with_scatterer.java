/*
 * waveguide_with_scatterer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:46 by COMSOL 6.3.0.290. */
public class waveguide_with_scatterer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
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

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("lda0", "1550[nm]");
    model.param().descr("lda0", "Wavelength");
    model.param().set("n_core", "1.5");
    model.param().descr("n_core", "Refractive index, core");
    model.param().set("n_cladding", "1");
    model.param().descr("n_cladding", "Refractive index, cladding");
    model.param().set("h_core", "1.25[um]");
    model.param().descr("h_core", "Thickness, core, section A");
    model.param().set("h_cladding", "8[um]");
    model.param().descr("h_cladding", "Thickness, cladding");
    model.param().set("L_core", "8[um]");
    model.param().descr("L_core", "Core length");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "Frequency");
    model.param().set("t_PML", "0.5[um]");
    model.param().descr("t_PML", "PML thickness");
    model.param().set("Pin", "1[W]");
    model.param().descr("Pin", "Power in");
    model.param().set("PN", "Pin[1/m]");
    model.param().descr("PN", "Power, normalized out-of-plane");
    model.param().set("NEL", "12");
    model.param().descr("NEL", "Number of elements/wavelength");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L_core", "h_core"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L_core", "h_cladding"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "h_core/10");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "h_core*0.65"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"L_core-3*t_PML", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "h_cladding-3*t_PML", 1);
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_cladding"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3, 8, 12, 14, 17);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_core"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3").label("Au (Gold) (Johnson and Christy 1972: n,k 0.188-1.937 um)");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.1879", "1.28"}, 
         {"0.1916", "1.32"}, 
         {"0.1953", "1.34"}, 
         {"0.1993", "1.33"}, 
         {"0.2033", "1.33"}, 
         {"0.2073", "1.30"}, 
         {"0.2119", "1.30"}, 
         {"0.2164", "1.30"}, 
         {"0.2214", "1.30"}, 
         {"0.2262", "1.31"}, 
         {"0.2313", "1.30"}, 
         {"0.2371", "1.32"}, 
         {"0.2426", "1.32"}, 
         {"0.2490", "1.33"}, 
         {"0.2551", "1.33"}, 
         {"0.2616", "1.35"}, 
         {"0.2689", "1.38"}, 
         {"0.2761", "1.43"}, 
         {"0.2844", "1.47"}, 
         {"0.2924", "1.49"}, 
         {"0.3009", "1.53"}, 
         {"0.3107", "1.53"}, 
         {"0.3204", "1.54"}, 
         {"0.3315", "1.48"}, 
         {"0.3425", "1.48"}, 
         {"0.3542", "1.50"}, 
         {"0.3679", "1.48"}, 
         {"0.3815", "1.46"}, 
         {"0.3974", "1.47"}, 
         {"0.4133", "1.46"}, 
         {"0.4305", "1.45"}, 
         {"0.4509", "1.38"}, 
         {"0.4714", "1.31"}, 
         {"0.4959", "1.04"}, 
         {"0.5209", "0.62"}, 
         {"0.5486", "0.43"}, 
         {"0.5821", "0.29"}, 
         {"0.6168", "0.21"}, 
         {"0.6595", "0.14"}, 
         {"0.7045", "0.13"}, 
         {"0.7560", "0.14"}, 
         {"0.8211", "0.16"}, 
         {"0.8920", "0.17"}, 
         {"0.9840", "0.22"}, 
         {"1.0880", "0.27"}, 
         {"1.2160", "0.35"}, 
         {"1.3930", "0.43"}, 
         {"1.6100", "0.56"}, 
         {"1.9370", "0.92"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.1879", "1.188"}, 
         {"0.1916", "1.203"}, 
         {"0.1953", "1.226"}, 
         {"0.1993", "1.251"}, 
         {"0.2033", "1.277"}, 
         {"0.2073", "1.304"}, 
         {"0.2119", "1.350"}, 
         {"0.2164", "1.387"}, 
         {"0.2214", "1.427"}, 
         {"0.2262", "1.460"}, 
         {"0.2313", "1.497"}, 
         {"0.2371", "1.536"}, 
         {"0.2426", "1.577"}, 
         {"0.2490", "1.631"}, 
         {"0.2551", "1.688"}, 
         {"0.2616", "1.749"}, 
         {"0.2689", "1.803"}, 
         {"0.2761", "1.847"}, 
         {"0.2844", "1.869"}, 
         {"0.2924", "1.878"}, 
         {"0.3009", "1.889"}, 
         {"0.3107", "1.893"}, 
         {"0.3204", "1.898"}, 
         {"0.3315", "1.883"}, 
         {"0.3425", "1.871"}, 
         {"0.3542", "1.866"}, 
         {"0.3679", "1.895"}, 
         {"0.3815", "1.933"}, 
         {"0.3974", "1.952"}, 
         {"0.4133", "1.958"}, 
         {"0.4305", "1.948"}, 
         {"0.4509", "1.914"}, 
         {"0.4714", "1.849"}, 
         {"0.4959", "1.833"}, 
         {"0.5209", "2.081"}, 
         {"0.5486", "2.455"}, 
         {"0.5821", "2.863"}, 
         {"0.6168", "3.272"}, 
         {"0.6595", "3.697"}, 
         {"0.7045", "4.103"}, 
         {"0.7560", "4.542"}, 
         {"0.8211", "5.083"}, 
         {"0.8920", "5.663"}, 
         {"0.9840", "6.350"}, 
         {"1.0880", "7.150"}, 
         {"1.2160", "8.145"}, 
         {"1.3930", "9.519"}, 
         {"1.6100", "11.21"}, 
         {"1.9370", "13.78"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat3").selection().set(20);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intPx");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(30, 31, 33);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intNx");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(23, 25, 27);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intPy");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().set(29);
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intNy");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop4").selection().set(24);
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").set("opname", "intD");
    model.component("comp1").cpl("intop5").selection().set(20);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("SPx", "intPx(up(ewfd.Poavx))/PN-ewfd.Ttotal");
    model.component("comp1").variable("var1").descr("SPx", "Scattering in Positive X");
    model.component("comp1").variable("var1").set("SNx", "intNx(-up(ewfd.Poavx))/PN-ewfd.Rtotal");
    model.component("comp1").variable("var1").descr("SNx", "Scattering in Negative X");
    model.component("comp1").variable("var1").set("SPy", "intPy(up(ewfd.Poavy))/PN");
    model.component("comp1").variable("var1").descr("SPy", "Scattering in Positive Y");
    model.component("comp1").variable("var1").set("SNy", "intNy(-up(ewfd.Poavy))/PN");
    model.component("comp1").variable("var1").descr("SNy", "Scattering in Negative Y");
    model.component("comp1").variable("var1").set("Loss", "intD(ewfd.Qrh)/PN");
    model.component("comp1").variable("var1").descr("Loss", "Loss in Material");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(2, 3, 4, 16, 17, 18);
    model.component("comp1").coordSystem("pml1").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml1").set("typicalWavelength", "2*pi/ewfd.beta_1");
    model.component("comp1").coordSystem().create("pml2", "PML");
    model.component("comp1").coordSystem("pml2").selection().set(1, 5, 6, 10, 15, 19);

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(23, 25, 27);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Numeric");
    model.component("comp1").physics("ewfd").feature("port1").set("Pin", "PN");
    model.component("comp1").physics("ewfd").feature("port1").set("PortSlit", true);
    model.component("comp1").physics("ewfd").feature("port1").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewfd").feature("port1").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(30, 31, 33);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Numeric");
    model.component("comp1").physics("ewfd").feature("port2").set("PortSlit", true);
    model.component("comp1").physics("ewfd").feature("port2").set("SlitType", "DomainBacked");
    model.component("comp1").physics("ewfd").feature("port2").set("PortOrientation", "ReversePort");
    model.component("comp1").physics("ewfd").feature().duplicate("port3", "port2");
    model.component("comp1").physics("ewfd").feature("port3").selection().set(23, 25, 27);
    model.component("comp1").physics("ewfd").feature().duplicate("port4", "port3");
    model.component("comp1").physics("ewfd").feature("port4").selection().set(30, 31, 33);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lda0/NEL/n_cladding");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.003);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "lda0/NEL/n_core");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.003);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothmaxiter", 8);
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothmaxdepth", 8);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(23, 24, 25, 27, 29, 30, 31, 33);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "2[nm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("shift", "n_core");
    model.study("std1").feature().duplicate("bma1", "bma");
    model.study("std1").feature("bma1").set("PortName", "2");
    model.study("std1").feature().duplicate("bma2", "bma1");
    model.study("std1").feature("bma2").set("PortName", "3");
    model.study("std1").feature("bma2").set("neigsactive", true);
    model.study("std1").feature("bma2").set("neigs", 2);
    model.study("std1").feature().duplicate("bma3", "bma2");
    model.study("std1").feature("bma3").set("PortName", "4");
    model.study("std1").feature().move("freq", 4);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1")
         .label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1")
         .set("expr", new String[]{"ewfd.Rport_1", "ewfd.Rport_3", "ewfd.Rtotal", "ewfd.Tport_2", "ewfd.Tport_4", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 1 (ewfd)");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "root.comp1.ewfd.normEbm_1");
    model.result("pg2").feature("line1").set("colortable", "RainbowLight");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").create("hght1", "Height");
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("posxexpr", "ewfd.aveport1(x)");
    model.result("pg2").feature("ann1").set("posyexpr", "ewfd.aveport1(y)");
    model.result("pg2").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg2").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_1)");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 2 (ewfd)");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "root.comp1.ewfd.normEbm_2");
    model.result("pg3").feature("line1").set("colortable", "RainbowLight");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").create("hght1", "Height");
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("posxexpr", "ewfd.aveport2(x)");
    model.result("pg3").feature("ann1").set("posyexpr", "ewfd.aveport2(y)");
    model.result("pg3").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg3").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_2)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 3 (ewfd)");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "root.comp1.ewfd.normEbm_3");
    model.result("pg4").feature("line1").set("colortable", "RainbowLight");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").create("hght1", "Height");
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("posxexpr", "ewfd.aveport3(x)");
    model.result("pg4").feature("ann1").set("posyexpr", "ewfd.aveport3(y)");
    model.result("pg4").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg4").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_3)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u6a21\u573a\uff0c\u7aef\u53e3 4 (ewfd)");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "root.comp1.ewfd.normEbm_4");
    model.result("pg5").feature("line1").set("colortable", "RainbowLight");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").create("hght1", "Height");
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("posxexpr", "ewfd.aveport4(x)");
    model.result("pg5").feature("ann1").set("posyexpr", "ewfd.aveport4(y)");
    model.result("pg5").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg5").feature("ann1")
         .set("text", "\u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387: eval(comp1.ewfd.neff_4)");
    model.result("pg1").run();
    model.result().numerical("gev1").remove("unit", 7);
    model.result().numerical("gev1").remove("descr", 7);
    model.result().numerical("gev1").remove("expr", new int[]{7});
    model.result().table("tbl1").clearTableData();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "SNx+SPx+SPy+SNy+Loss", 0);
    model.result().numerical("gev2").setIndex("descr", "Material and Scattering Losses", 0);
    model.result().numerical("gev2").setIndex("expr", "SPy+SNy+SPx+SNx", 1);
    model.result().numerical("gev2").setIndex("descr", "Scattering Losses", 1);
    model.result().numerical("gev2").setIndex("expr", "Loss", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().set(7, 8, 9, 11, 12, 13, 14, 20);
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").run();
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().set();
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().set(20);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "ewfd.Qrh");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");

    model.title("\u5149\u6ce2\u5bfc\u9644\u8fd1\u7684\u6563\u5c04\u4f53\u5efa\u6a21");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u4e00\u4e2a\u4f4d\u4e8e\u5149\u6ce2\u5bfc\u9644\u8fd1\u7684\u5c0f\u578b\u6709\u635f\u6563\u5c04\u4f53\uff0c\u4ee5\u5c55\u793a\u5b83\u4e0e\u573a\u7684\u76f8\u4e92\u4f5c\u7528\uff0c\u5e76\u8ba1\u7b97\u4e86\u6cbf\u6ce2\u5bfc\u7684\u53cd\u5c04\u3001\u900f\u5c04\u3001\u635f\u8017\u53ca\u6563\u5c04\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("waveguide_with_scatterer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
