/*
 * polarizing_beam_splitter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:35 by COMSOL 6.3.0.290. */
public class polarizing_beam_splitter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

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
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wl0", "550[nm]", "Center wavelength");
    model.param().set("dWl", "500[nm]", "Bandwidth");
    model.param().set("w0", "20*wl0", "Spot radius");
    model.param().set("nPrism", "1.673", "Refractive index of the glass prisms");
    model.param().set("nHigh", "2.3", "Refractive index of the high-index materials");
    model.param().set("nLow", "1.38", "Refractive index of the low-index material");
    model.param().set("z0", "pi*nPrism*w0^2/wl0", "Rayleigh range");
    model.param().set("a", "8*w0/sqrt(1-(8*w0/z0)^2)", "Prism side");
    model.param().set("nLayers", "7", "Number of dielectric layers");
    model.param().set("dHigh", "wl0/4/nHigh/cos(alphaHigh)", "Thickness of high-index material layers");
    model.param().set("dLow", "wl0/4/nLow/cos(alphaLow)", "Thickness of low-index material layers");
    model.param().set("E0", "1[V/m]", "Peak incident electric field");
    model.param().set("lda0", "wl0", "Wavelength in vacuum");
    model.param().set("k0", "2*pi/lda0", "Wave number in vacuum");
    model.param().set("kParallel", "k0*nPrism/sqrt(2)", "Wave vector component parallel to the boundaries");
    model.param()
         .set("kHighNormal", "k0*sqrt(nHigh^2-nPrism^2/2)", "Wave vector component in high-index materials in the normal direction");
    model.param()
         .set("kLowNormal", "k0*sqrt(nLow^2-nPrism^2/2)", "Wave vector component in the normal direction for low-index materials");
    model.param().set("dStack", "floor(nLayers/2)*(dHigh+dLow)+dHigh", "Thickness of thin-film stack");
    model.param().set("wlCount", "11", "Number of wavelengths in the sweep");
    model.param().set("alphaHigh", "asin(nPrism*sin(pi/4)/nHigh)", "Angle in high-index material");
    model.param().set("alphaLow", "asin(nPrism*sin(pi/4)/nLow)", "Angle in low-index material");
    model.param().set("xCOGu", "-(a/2+dStack/sqrt(2))/3", "Center of gravity, upper triangle, x-coordinate");
    model.param().set("yCOGu", "-xCOGu", "Center of gravity, upper triangle, y-coordinate");
    model.param().set("xCOGl", "-xCOGu", "Center of gravity, lower triangle, x-coordinate");
    model.param().set("yCOGl", "xCOGu", "Center of gravity, lower triangle, y-coordinate");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("Thin-Film Stack");
    model.geom("part1").inputParam().set("nLayers", "3");
    model.geom("part1").inputParam().descr("nLayers", "Number of layers");
    model.geom("part1").inputParam().set("dHigh", "1[um]");
    model.geom("part1").inputParam().descr("dHigh", "Thickness of high-index material");
    model.geom("part1").inputParam().set("dLow", "2[um]");
    model.geom("part1").inputParam().descr("dLow", "Thickness of low-index material");
    model.geom("part1").inputParam().set("height", "5[um]");
    model.geom("part1").inputParam().descr("height", "Height of layers");
    model.geom("part1").create("pch1", "ParameterCheck");
    model.geom("part1").feature("pch1").set("condition", "nLayers<1");
    model.geom("part1").feature("pch1").set("message", "The number of layers must be a positive odd number");
    model.geom("part1").feature().duplicate("pch2", "pch1");
    model.geom("part1").feature("pch2").set("condition", "mod(nLayers,2)==0");
    model.geom("part1").run("pch2");
    model.geom("part1").create("r1", "Rectangle");
    model.geom("part1").feature("r1").label("First High-Index Layer");
    model.geom("part1").feature("r1").set("size", new String[]{"dHigh", "height"});
    model.geom("part1").feature("r1").set("pos", new String[]{"-floor(nLayers/2)/2*(dHigh+dLow)-dHigh/2", "0"});
    model.geom("part1").feature("r1").setIndex("pos", "-height/2", 1);
    model.geom("part1").selection().create("csel1", "CumulativeSelection");
    model.geom("part1").selection("csel1").label("High-Index Material");
    model.geom("part1").feature("r1").set("contributeto", "csel1");
    model.geom("part1").run("r1");
    model.geom("part1").create("if1", "If");
    model.geom("part1").feature().createAfter("endif1", "EndIf", "if1");
    model.geom("part1").feature("if1").set("condition", "nLayers > 1");
    model.geom("part1").create("copy1", "Copy");
    model.geom("part1").feature("copy1").label("Last High-Index Layer");
    model.geom("part1").feature("copy1").selection("input").set("r1");
    model.geom("part1").feature("copy1").set("displx", "floor(nLayers/2)*(dHigh+dLow)");
    model.geom("part1").feature("copy1").set("contributeto", "csel1");
    model.geom("part1").feature().duplicate("r2", "r1");
    model.geom("part1").feature("r2").label("First Low-Index Layer");
    model.geom("part1").feature("r2").set("size", new String[]{"dLow", "height"});
    model.geom("part1").feature("r2")
         .set("pos", new String[]{"-floor(nLayers/2)/2*(dHigh+dLow)+dHigh/2", "-height/2"});
    model.geom("part1").selection().create("csel2", "CumulativeSelection");
    model.geom("part1").selection("csel2").label("Low-Index Material");
    model.geom("part1").feature("r2").set("contributeto", "csel2");
    model.geom("part1").run("r2");
    model.geom("part1").create("arr1", "Array");
    model.geom("part1").feature("arr1").selection("input").set("r1", "r2");
    model.geom("part1").feature("arr1").set("fullsize", new String[]{"floor(nLayers/2)", "1"});
    model.geom("part1").feature("arr1").set("displ", new String[]{"dHigh+dLow", "0"});
    model.geom("part1").selection().create("csel3", "CumulativeSelection");
    model.geom("part1").selection("csel3").label("Thin-Film Stack");
    model.geom("part1").run("arr1");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").feature("uni1").selection("input").set("arr1", "copy1");
    model.geom("part1").feature("uni1").set("contributeto", "csel3");
    model.geom("part1").feature().move("uni1", 8);
    model.geom("part1").run("uni1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nLayers", "nLayers");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dHigh", "dHigh");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dLow", "dLow");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "height", "sqrt(2)*a-dStack");
    model.component("comp1").geom("geom1").feature("pi1").set("rot", -45);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel1", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel2", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel3", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel3", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel1.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowdom", "pi1_csel1.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel2.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowdom", "pi1_csel2.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel2.dom", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel3.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowdom", "pi1_csel3.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel3.dom", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel1.bnd", false);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel2.bnd", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowbnd", "pi1_csel2.bnd", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_csel3.bnd", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowbnd", "pi1_csel3.bnd", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel3.bnd", "none");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-a/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-a/2+dStack/sqrt(2)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dStack/sqrt(2)/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "dStack/sqrt(2)/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "xCOGu", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "yCOGu", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-a/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "dStack/sqrt(2)/2", 3, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pol2", "pol1");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "xCOGu", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "yCOGu", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-a/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "dStack/sqrt(2)/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-a/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "a/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dStack/sqrt(2)/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "a/2", 3, 1);
    model.component("comp1").geom("geom1").feature().duplicate("pol3", "pol2");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "-dStack/sqrt(2)/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "a/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "xCOGu", 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "yCOGu", 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "-dStack/sqrt(2)/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "dStack/sqrt(2)/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "a/2-dStack/sqrt(2)", 3, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "a/2", 3, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pol1", "pol2", "pol3");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Prisms");
    model.component("comp1").geom("geom1").feature("uni1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 5, 21, 22, 25, 27, 29);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Prisms");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"nPrism"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("High-Index Material");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nHigh"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Low-Index Material");
    model.component("comp1").material("mat3").selection().named("geom1_pi1_csel2_dom");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"nLow"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.material().create("mat4", "Common", "");
    model.material("mat4").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.material("mat4").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott thermo-optic");
    model.material("mat4").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat4").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "Internal transmittance, 10\u00a0mm sample thickness");
    model.material("mat4").propertyGroup("InternalTransmittance10").func().create("int1", "Interpolation");
    model.material("mat4").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "Internal transmittance, 25\u00a0mm sample thickness");
    model.material("mat4").propertyGroup("InternalTransmittance25").func().create("int1", "Interpolation");
    model.material("mat4").label("Schott N-SF5 Glass");
    model.material("mat4").propertyGroup("def").set("density", "2.86[g/cm^3]");
    model.material("mat4").propertyGroup("def").set("heatcapacity", "0.77[J/(g*K)]");
    model.material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]", "0", "0", "0", "1.0[W/(m*K)]"});
    model.material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]", "0", "0", "0", "7.94E-6[1/K]"});
    model.material("mat4").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.52481889E+00", "1.87085527E-01", "1.42729015E+00", "1.12547560E-02", "5.88995392E-02", "1.29141675E+02"});
    model.material("mat4").propertyGroup("DispersionModelSellmeierStandard").set("Trefsma", "22[degC]");
    model.material("mat4").propertyGroup("DispersionModelSellmeierStandard").set("Prefsma", "1[atm]");
    model.material("mat4").propertyGroup("DispersionModelSellmeierStandard").addInput("frequency");
    model.material("mat4").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"-2.51E-7", "1.07E-8", "-2.4E-11", "7.85E-7", "1.15E-9", "0.278"});
    model.material("mat4").propertyGroup("ThermoOpticDispersionModelSchott").set("Trefsco", "20[degC]");
    model.material("mat4").propertyGroup("Enu").set("E", "87.0[GPa]");
    model.material("mat4").propertyGroup("Enu").set("nu", "0.237");
    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1").set("funcname", "taui10");
    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.758"}, 
         {"2325", "0.831"}, 
         {"1970", "0.95"}, 
         {"1530", "0.99"}, 
         {"1060", "0.998"}, 
         {"700", "0.996"}, 
         {"660", "0.995"}, 
         {"620", "0.995"}, 
         {"580", "0.996"}, 
         {"546", "0.995"}, 
         {"500", "0.99"}, 
         {"460", "0.982"}, 
         {"436", "0.973"}, 
         {"420", "0.963"}, 
         {"405", "0.928"}, 
         {"400", "0.905"}, 
         {"390", "0.826"}, 
         {"380", "0.642"}, 
         {"370", "0.276"}, 
         {"365", "0.116"}});
    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1").set("extrap", "none");
    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1").set("fununit", new String[]{"1"});
    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1").set("argunit", new String[]{"nm"});
    model.material("mat4").propertyGroup("InternalTransmittance10").set("taui10", "taui10(c_const/freq)");
    model.material("mat4").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1").set("funcname", "taui25");
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.5"}, 
         {"2325", "0.63"}, 
         {"1970", "0.88"}, 
         {"1530", "0.975"}, 
         {"1060", "0.994"}, 
         {"700", "0.989"}, 
         {"660", "0.987"}, 
         {"620", "0.988"}, 
         {"580", "0.991"}, 
         {"546", "0.988"}, 
         {"500", "0.976"}, 
         {"460", "0.956"}, 
         {"436", "0.935"}, 
         {"420", "0.91"}, 
         {"405", "0.83"}, 
         {"400", "0.78"}, 
         {"390", "0.62"}, 
         {"380", "0.33"}, 
         {"370", "0.04"}});
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1").set("extrap", "none");
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1").set("fununit", new String[]{"1"});
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1").set("argunit", new String[]{"nm"});
    model.material("mat4").propertyGroup("InternalTransmittance25").set("taui25", "taui25(c_const/freq)");
    model.material("mat4").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.material().create("mat5", "Common", "");
    model.material("mat5").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat5").propertyGroup("RefractiveIndex").func().create("an1", "Analytic");
    model.material("mat5").label("ZnS (Zinc sulfide) (Debenham 1984: Cubic ZnS; n 0.405-13 um)");
    model.material("mat5").propertyGroup("RefractiveIndex").func("an1")
         .set("expr", "sqrt((8.393)+(0.14383)/(x^2-0.058612410000000004)+(4430.99)/(x^2-1347.6241))");
    model.material("mat5").propertyGroup("RefractiveIndex").func("an1").set("fununit", "1");
    model.material("mat5").propertyGroup("RefractiveIndex").func("an1").set("argunit", new String[]{"um"});
    model.material("mat5").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)"});
    model.material("mat5").propertyGroup("RefractiveIndex").addInput("frequency");
    model.material().create("mat6", "Common", "");
    model.material("mat6").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat6").propertyGroup("RefractiveIndex").func().create("an1", "Analytic");
    model.material("mat6").label("MgF2 (Magnesium fluoride) (Li 1980: n(o) 0.14-7.5 um)");
    model.material("mat6").propertyGroup("RefractiveIndex").func("an1")
         .set("expr", "sqrt((1.2762)+(0.60967)*x^2/(x^2-(0.007458049600000001))+(0.0080)*x^2/(x^2-(324.0))+(2.14973)*x^2/(x^2-(625.0)))");
    model.material("mat6").propertyGroup("RefractiveIndex").func("an1").set("fununit", "1");
    model.material("mat6").propertyGroup("RefractiveIndex").func("an1").set("argunit", new String[]{"um"});
    model.material("mat6").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)"});
    model.material("mat6").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("matlnk1").active(false);
    model.component("comp1").material().duplicate("matlnk2", "matlnk1");
    model.component("comp1").material("matlnk2").selection().named("geom1_pi1_csel1_dom");
    model.component("comp1").material("matlnk2").set("link", "mat5");
    model.component("comp1").material().duplicate("matlnk3", "matlnk2");
    model.component("comp1").material("matlnk3").selection().named("geom1_pi1_csel2_dom");
    model.component("comp1").material("matlnk3").set("link", "mat6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("Positions");
    model.component("comp1").variable("var1").set("x0", "-dStack/sqrt(2)");
    model.component("comp1").variable("var1").descr("x0", "Boundary 0");
    model.component("comp1").variable("var1").set("x1", "x0+sqrt(2)*dHigh");
    model.component("comp1").variable("var1").descr("x1", "Boundary 1");
    model.component("comp1").variable("var1").set("x2", "x1+sqrt(2)*dLow");
    model.component("comp1").variable("var1").descr("x2", "Boundary 2");
    model.component("comp1").variable("var1").set("x3", "x2+sqrt(2)*dHigh");
    model.component("comp1").variable("var1").descr("x3", "Boundary 3");
    model.component("comp1").variable("var1").set("x4", "x3+sqrt(2)*dLow");
    model.component("comp1").variable("var1").descr("x4", "Boundary 4");
    model.component("comp1").variable("var1").set("x5", "x4+sqrt(2)*dHigh");
    model.component("comp1").variable("var1").descr("x5", "Boundary 5");
    model.component("comp1").variable("var1").set("x6", "x5+sqrt(2)*dLow");
    model.component("comp1").variable("var1").descr("x6", "Boundary 6");
    model.component("comp1").variable("var1").set("x7", "x6+sqrt(2)*dHigh");
    model.component("comp1").variable("var1").descr("x7", "Boundary 7");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("Wave Vector Components");
    model.component("comp1").variable("var2").set("k1Prismx", "k0*nP");
    model.component("comp1").variable("var2").descr("k1Prismx", "Wave vector in prisms, first wave, x-component");
    model.component("comp1").variable("var2").set("k1Prismy", "0[1/m]");
    model.component("comp1").variable("var2").descr("k1Prismy", "Wave vector in prisms, first wave, y-component");
    model.component("comp1").variable("var2").set("k1Highx", "(kt+kHn)/sqrt(2)");
    model.component("comp1").variable("var2")
         .descr("k1Highx", "Wave vector in high-index materials, first wave, x-component");
    model.component("comp1").variable("var2").set("k1Highy", "(kt-kHn)/sqrt(2)");
    model.component("comp1").variable("var2")
         .descr("k1Highy", "Wave vector in high-index materials, first wave, y-component");
    model.component("comp1").variable("var2").set("k1Lowx", "(kt+kLn)/sqrt(2)");
    model.component("comp1").variable("var2")
         .descr("k1Lowx", "Wave vector in low-index materials, first wave, x-component");
    model.component("comp1").variable("var2").set("k1Lowy", "(kt-kLn)/sqrt(2)");
    model.component("comp1").variable("var2")
         .descr("k1Lowy", "Wave vector in low-index materials, first wave, y-component");
    model.component("comp1").variable("var2").set("k2Prismx", "k1Prismy");
    model.component("comp1").variable("var2").descr("k2Prismx", "Wave vector in prisms, second wave, x-component");
    model.component("comp1").variable("var2").set("k2Prismy", "k1Prismx");
    model.component("comp1").variable("var2").descr("k2Prismy", "Wave vector in prisms, second wave, y-component");
    model.component("comp1").variable("var2").set("k2Highx", "k1Highy");
    model.component("comp1").variable("var2")
         .descr("k2Highx", "Wave vector in high-index materials, second wave, x-component");
    model.component("comp1").variable("var2").set("k2Highy", "k1Highx");
    model.component("comp1").variable("var2")
         .descr("k2Highy", "Wave vector in high-index materials, second wave, y-component");
    model.component("comp1").variable("var2").set("k2Lowx", "k1Lowy");
    model.component("comp1").variable("var2")
         .descr("k2Lowx", "Wave vector in low-index materials, second wave, x-component");
    model.component("comp1").variable("var2").set("k2Lowy", "k1Lowx");
    model.component("comp1").variable("var2")
         .descr("k2Lowy", "Wave vector in low-index materials, second wave, y-component");

    model.component("comp1").selection().create("disk1", "Disk");
    model.component("comp1").selection("disk1").label("First Prism");
    model.component("comp1").selection("disk1").set("posx", "-a/4");
    model.component("comp1").selection("disk1").set("posy", "a/4");
    model.component("comp1").selection().duplicate("disk2", "disk1");
    model.component("comp1").selection("disk2").label("Second Prism");
    model.component("comp1").selection("disk2").set("posx", "a/4");
    model.component("comp1").selection("disk2").set("posy", "-a/4");

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("Phases in First Prism");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("disk1");
    model.component("comp1").variable("var3").set("phi1", "k1Prismx*x+k1Prismy*y+phi100");
    model.component("comp1").variable("var3").descr("phi1", "Phase for first wave");
    model.component("comp1").variable("var3").set("phi2", "k2Prismx*x+k2Prismy*y+phi200");
    model.component("comp1").variable("var3").descr("phi2", "Phase for second wave");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").label("Phases in First High-Index Layer");
    model.component("comp1").variable("var4").selection().set(2);
    model.component("comp1").variable("var4").set("phi1", "k1Highx*x+k1Highy*y+phi110");
    model.component("comp1").variable("var4").set("phi2", "k2Highx*x+k2Highy*y+phi210");
    model.component("comp1").variable().duplicate("var5", "var4");
    model.component("comp1").variable("var5").label("Phases in First Low-Index Layer 1");
    model.component("comp1").variable("var5").selection().set(3);
    model.component("comp1").variable("var5").set("phi1", "k1Lowx*x+k1Lowy*y+phi120");
    model.component("comp1").variable("var5").set("phi2", "k2Lowx*x+k2Lowy*y+phi220");
    model.component("comp1").variable().duplicate("var6", "var4");
    model.component("comp1").variable("var6").label("Phases in Second High-Index Layer");
    model.component("comp1").variable("var6").selection().set(4);
    model.component("comp1").variable("var6").set("phi1", "k1Highx*x+k1Highy*y+phi130");
    model.component("comp1").variable("var6").set("phi2", "k2Highx*x+k2Highy*y+phi230");
    model.component("comp1").variable("var5").label("Phases in First Low-Index Layer");
    model.component("comp1").variable().duplicate("var7", "var5");
    model.component("comp1").variable("var7").label("Phases in Second Low-Index Layer");
    model.component("comp1").variable("var7").selection().set(5);
    model.component("comp1").variable("var7").set("phi1", "k1Lowx*x+k1Lowy*y");
    model.component("comp1").variable("var7").set("phi2", "k2Lowx*x+k2Lowy*y");
    model.component("comp1").variable().duplicate("var8", "var6");
    model.component("comp1").variable("var8").label("Phases in Third High-Index Layer");
    model.component("comp1").variable("var8").selection().set(6);
    model.component("comp1").variable("var8").set("phi1", "k1Highx*x+k1Highy*y+phi150");
    model.component("comp1").variable("var8").set("phi2", "k2Highx*x+k2Highy*y+phi250");
    model.component("comp1").variable().duplicate("var9", "var7");
    model.component("comp1").variable("var9").label("Phases in Third Low-Index Layer");
    model.component("comp1").variable("var9").selection().set(7);
    model.component("comp1").variable("var9").set("phi1", "k1Lowx*x+k1Lowy*y+phi160");
    model.component("comp1").variable("var9").set("phi2", "k2Lowx*x+k2Lowy*y+phi260");
    model.component("comp1").variable().duplicate("var10", "var8");
    model.component("comp1").variable("var10").selection().set(8);
    model.component("comp1").variable("var10").set("phi1", "k1Highx*x+k1Highy*y+phi170");
    model.component("comp1").variable("var10").set("phi2", "k2Highx*x+k2Highy*y+phi270");
    model.component("comp1").variable("var10").label("Phases in Fourth High-Index Layer");
    model.component("comp1").variable().duplicate("var11", "var3");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").variable("var11").label("Phases in Second Prism");
    model.component("comp1").variable("var11").selection().set();
    model.component("comp1").variable("var11").selection().named("disk2");
    model.component("comp1").variable("var11").set("phi1", "k1Prismx*x+k1Prismy*y+phi180");
    model.component("comp1").variable("var11").set("phi2", "k2Prismx*x+k2Prismy*y+phi280");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").label("Exterior Boundaries");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Thin-Film Stack Exterior Boundaries");
    model.component("comp1").selection("int1").set("entitydim", 1);
    model.component("comp1").selection("int1").set("input", new String[]{"sel1", "geom1_pi1_csel3_bnd"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").set("inputent", "selections");
    model.component("comp1").selection("box1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("box1").set("xmax", "-a/2+dHigh/2/sqrt(2)");
    model.component("comp1").selection("box1").set("ymin", "-a/2");
    model.component("comp1").selection("box1").set("ymax", "a/2");
    model.component("comp1").selection("box1").set("condition", "allvertices");
    model.component("comp1").selection("box1").label("Left Boundary");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("Top Boundary");
    model.component("comp1").selection("box2").set("xmin", "-a/2");
    model.component("comp1").selection("box2").set("xmax", "a/2");
    model.component("comp1").selection("box2").set("ymin", "a/2-dHigh/2/sqrt(2)");
    model.component("comp1").selection("box2").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection().duplicate("box3", "box2");
    model.component("comp1").selection("box3").label("Right Boundary");
    model.component("comp1").selection("box3").set("xmin", "a/2-dHigh/2/sqrt(2)");
    model.component("comp1").selection("box3").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").selection("box3").set("ymin", "-a/2");
    model.component("comp1").selection("box3").set("ymax", "a/2");
    model.component("comp1").selection().duplicate("box4", "box3");
    model.component("comp1").selection("box4").label("Bottom Boundary");
    model.component("comp1").selection("box4").set("xmin", "-a/2");
    model.component("comp1").selection("box4").set("xmax", "a/2");
    model.component("comp1").selection("box4").set("ymin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box4").set("ymax", "-a/2+dHigh/2/sqrt(2)");
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").label("Top-Right Thin-Film Exterior Boundaries");
    model.component("comp1").selection("box5").set("entitydim", 1);
    model.component("comp1").selection("box5").set("xmin", 0);
    model.component("comp1").selection("box5").set("ymin", 0);
    model.component("comp1").selection("box5").set("condition", "inside");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("box1");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().named("box2");
    model.component("comp1").cpl().duplicate("intop3", "intop2");
    model.component("comp1").cpl("intop3").selection().named("box3");
    model.component("comp1").cpl().duplicate("intop4", "intop3");
    model.component("comp1").cpl("intop4").selection().named("box4");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("geom1_csel1_dom");
    model.component("comp1").cpl("aveop1").label("Prism Average");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().named("geom1_pi1_csel1_dom");
    model.component("comp1").cpl("aveop2").label("High-Index Average");
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").label("Low-Index Average");
    model.component("comp1").cpl("aveop3").selection().named("geom1_pi1_csel2_dom");

    model.component("comp1").variable().create("var12");
    model.component("comp1").variable("var12").label("Power");
    model.component("comp1").variable("var12").set("Pin", "-intop1(ewbe.nPoav)");
    model.component("comp1").variable("var12").descr("Pin", "Input beam power");
    model.component("comp1").variable("var12").set("Ptop", "intop2(ewbe.nPoav)");
    model.component("comp1").variable("var12").descr("Ptop", "Top beam power");
    model.component("comp1").variable("var12").set("Pright", "intop3(ewbe.nPoav)");
    model.component("comp1").variable("var12").descr("Pright", "Right beam power");
    model.component("comp1").variable("var12").set("Pbottom", "intop4(ewbe.nPoav)");
    model.component("comp1").variable("var12").descr("Pbottom", "Bottom beam power");
    model.component("comp1").variable().create("var13");
    model.component("comp1").variable("var13").label("Refractive Indices and Wave Vector Components");
    model.component("comp1").variable("var13").set("nP", "aveop1(ewbe.nxx)");
    model.component("comp1").variable("var13").descr("nP", "Refractive index, prisms");
    model.component("comp1").variable("var13").set("nH", "aveop2(ewbe.nxx)");
    model.component("comp1").variable("var13").descr("nH", "Refractive index, high-index material");
    model.component("comp1").variable("var13").set("nL", "aveop3(ewbe.nxx)");
    model.component("comp1").variable("var13").descr("nL", "Refractive index, low-index material");
    model.component("comp1").variable("var13").set("kt", "k0*nP/sqrt(2)");
    model.component("comp1").variable("var13").descr("kt", "Tangential wave vector component");
    model.component("comp1").variable("var13").set("kHn", "k0*sqrt(nH^2-nP^2/2)");
    model.component("comp1").variable("var13").descr("kHn", "Normal wave vector component, high-index material");
    model.component("comp1").variable("var13").set("kLn", "k0*sqrt(nL^2-nP^2/2)");
    model.component("comp1").variable("var13").descr("kLn", "Normal wave vector component, low-index material");

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi1");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase2", "phi2");
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().named("box1");
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "EField");
    model.component("comp1").physics("ewbe").feature("mbc1").set("E0i", new String[]{"0", "0", "E0*exp(-(y/w0)^2)"});
    model.component("comp1").physics("ewbe").feature("mbc1").set("NoScatteredField", true);
    model.component("comp1").physics("ewbe").feature().duplicate("mbc2", "mbc1");
    model.component("comp1").physics("ewbe").feature("mbc2").selection().named("box2");
    model.component("comp1").physics("ewbe").feature("mbc2").set("IncidentField", "NoIncidentField");
    model.component("comp1").physics("ewbe").feature("mbc2").set("NoScatteredField", false);
    model.component("comp1").physics("ewbe").feature().duplicate("mbc3", "mbc2");
    model.component("comp1").physics("ewbe").feature("mbc3").selection().named("box3");
    model.component("comp1").physics("ewbe").feature("mbc3").set("InputWave", "SecondWave");
    model.component("comp1").physics("ewbe").feature().duplicate("mbc4", "mbc3");
    model.component("comp1").physics("ewbe").feature("mbc4").selection().named("box4");
    model.component("comp1").physics("ewbe").feature("mbc4").set("NoScatteredField", true);
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().named("box5");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "planeangle");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "planeangle");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi100", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Prismx*x0+phi100-k1Highx*x0-phi110", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 0", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi110", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Highx*x1+phi110-k1Lowx*x1-phi120", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 1", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi120", 2, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 2, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 2, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 2, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 2, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Lowx*x2+phi120-k1Highx*x2-phi130", 2, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 2", 2, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi130", 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "k1Highx*x3+phi130-k1Lowx*x3", 3, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 3", 3, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi150", 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "k1Lowx*x4-k1Highx*x4-phi150", 4, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 5", 4, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi160", 5, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 5, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 5, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 5, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 5, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Highx*x5+phi150-k1Lowx*x5-phi160", 5, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 6", 5, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi170", 6, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 6, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 6, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 6, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 6, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Lowx*x6+phi160-k1Highx*x6-phi170", 6, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 7", 6, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi180", 7, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 7, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 7, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 7, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 7, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k1Highx*x7+phi170-k1Prismx*x7-phi180", 7, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, first wave, layer 8", 7, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi200", 8, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 8, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 8, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 8, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 8, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Prismy*x7+phi200-k2Highy*x7-phi210", 8, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 0", 8, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi210", 9, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 9, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 9, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 9, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 9, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Highy*x6+phi210-k2Lowy*x6-phi220", 9, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 1", 9, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi220", 10, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 10, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 10, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 10, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 10, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Lowy*x5+phi220-k2Highy*x5-phi230", 10, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 2", 10, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi230", 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "k2Highy*x4+phi230-k2Lowy*x4", 11, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 3", 11, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi250", 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "k2Lowy*x3-k2Highy*x3-phi250", 12, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 5", 12, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi260", 13, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 13, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 13, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 13, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 13, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Highy*x2+phi250-k2Lowy*x2-phi260", 13, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 6", 13, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi270", 14, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 14, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 14, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 14, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 14, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Lowy*x1+phi260-k2Highy*x1-phi270", 14, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 7", 14, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "phi280", 15, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 15, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 15, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 15, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 15, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "k2Highy*x0+phi270-k2Prismy*x0-phi280", 15, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Phase constant, second wave, layer 8", 15, 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "w0/4");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_pi1_csel3_dom");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("int1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 0);
    model.study("std1").feature("freq").set("plist", "c_const/lda0");
    model.study("std1").feature("freq").setSolveFor("/physics/ge", false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "wl0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "range(wl0-dWl/2,(wl0+dWl/2-(wl0-dWl/2))/(wlCount-1),wl0+dWl/2)", 0);
    model.study("std1").feature("param").setIndex("punit", "nm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("Electric Field (ewbe)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").set("looplevel", new int[]{1, 6});
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Electric Field");
    model.result("pg1").feature("surf1").set("resolution", "extrafine");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"Ptop"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"Top beam power"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"W/m"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"Ptop", "Pright"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"Top beam power", "Right beam power"});
    model.result("pg2").feature("glob1").setIndex("expr", "Ptop/Pin", 0);
    model.result("pg2").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg2").feature("glob1").setIndex("descr", "R<sub>up</sub>", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "Pright/Pin", 1);
    model.result("pg2").feature("glob1").setIndex("unit", 1, 1);
    model.result("pg2").feature("glob1").setIndex("descr", "T", 1);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "lda0");
    model.result("pg2").feature("glob1").set("xdataunit", "nm");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "R<sub>up,s</sub>", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "T<sub>s</sub>", 1);
    model.result("pg2").run();

    model.sol("sol3").copySolution("sol15");

    model.component("comp1").physics("ewbe").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewbe").feature("mbc1").set("E0i", new String[]{"0", "E0*exp(-(y/w0)^2)", "0"});

    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("data", "dset4");
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset3");
    model.result("pg2").feature("glob2").setIndex("legends", "R<sub>up,p</sub>", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "T<sub>p</sub>", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Reflectance and transmittance");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "Upward reflectance and straight-through transmittance for s- and p-polarization");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset3");
    model.result().dataset("cln1").setIndex("genpoints", "-dStack", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "dStack", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "dStack", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-dStack", 1, 1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "cln1");
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Refractive index");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "ewbe.nxx");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("xdataunit", "nm");

    model.common("cminpt").set("modified", new String[][]{{"frequency", "c_const/lda0"}});

    model.material("mat4").propertyGroup("InternalTransmittance10").func("int1").set("extrap", "const");
    model.material("mat4").propertyGroup("InternalTransmittance25").func("int1").set("extrap", "const");
    model.component("comp1").material("matlnk1").active(true);

    model.component("comp1").physics("ewbe").feature("webe1").set("minput_frequency_src", "fromCommonDef");

    model.component("comp1").material("matlnk1").active(false);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("r1");
    model.component("comp2").geom("geom2").feature("arr1").set("fullsize", new int[]{3, 1});
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new int[]{1, 0});
    model.component("comp2").geom("geom2").run();

    model.material().label("Dummy Component for Material Property Evaluation");
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").label("SF5");
    model.component("comp2").material().duplicate("matlnk5", "matlnk4");
    model.component("comp2").material("matlnk5").label("ZnS");
    model.component("comp2").material("matlnk5").set("link", "mat5");
    model.component("comp2").material("matlnk5").selection().set(2);
    model.component("comp2").material().duplicate("matlnk6", "matlnk5");
    model.component("comp2").material("matlnk6").label("MgF2");
    model.component("comp2").material("matlnk6").set("link", "mat6");
    model.component("comp2").material("matlnk6").selection().set(3);

    model.component("comp2").physics().create("ewbe2", "ElectromagneticWavesBeamEnvelopes", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ewbe2", false);
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe2", false);

    model.component("comp2").physics("ewbe2").prop("MeshControl").set("elemCountT", 1);
    model.component("comp2").physics("ewbe2").prop("MeshControl").set("elemCountL", 3);

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ge", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe2", true);
    model.study("std2").label("Dummy Study for Material Property Evaluation");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("wave").set("plist", "range(500[nm],(5[um]-(500[nm]))/99,5[um])");
    model.study("std2").createAutoSequences("all");

    model.sol("sol38").runAll();

    model.result().dataset().create("av1", "Average");
    model.result().dataset("av1").set("intsurface", true);
    model.result().dataset("av1").set("intvolume", true);
    model.result().dataset("av1").label("Average, SF5");
    model.result().dataset("av1").set("data", "dset6");
    model.result().dataset("av1").set("showlevel", "off");
    model.result().dataset("av1").selection().geom("geom2", 2);

    return model;
  }

  public static Model run3(Model model) {
    model.result().dataset("av1").selection().set(1);
    model.result().dataset().duplicate("av2", "av1");
    model.result().dataset("av2").label("Average, ZnS");
    model.result().dataset("av2").selection().set(2);
    model.result().dataset().duplicate("av3", "av2");
    model.result().dataset("av3").label("Average, MgF2");
    model.result().dataset("av3").selection().set(3);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Refractive Index Spectra");
    model.result("pg4").set("data", "av1");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").setIndex("expr", "ewbe2.nxx", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Refractive index, real part", 0);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "ewbe2.kixx", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "Refractive index, imaginary part", 0);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Wavelength (\u00b5m)");
    model.result("pg4").set("titletype", "none");

    model.title("\u504f\u632f\u5149\u675f\u5206\u675f\u5668");

    model
         .description("\u4e00\u675f\u9ad8\u65af\u5149\u675f\u5165\u5c04\u5230\u4e00\u4e2a\u5d4c\u5165\u73bb\u7483\u6750\u6599\u68f1\u955c\u7684 45 \u5ea6\u8584\u819c\u53e0\u5c42\u4e0a\u3002\u8fd9\u4e2a\u8584\u819c\u53e0\u5c42\u91c7\u7528\u9ad8\u548c\u4f4e\u6298\u5c04\u7387\u6750\u6599\u4ea4\u66ff\u6392\u5217\u7684\u8bbe\u8ba1\uff0c\u6ce2\u5c06\u4f1a\u5728\u6bcf\u4e2a\u5185\u90e8\u754c\u9762\u4ee5 Brewster \u89d2\u8fdb\u884c\u6298\u5c04\u3002\u56e0\u6b64\u5927\u90e8\u5206\u7684 p \u504f\u632f\u6ce2\uff08\u504f\u632f\u4e0e\u5165\u5c04\u9762\u4e00\u81f4\uff09\u4f1a\u53d1\u751f\u900f\u5c04\uff0c\u800c\u5927\u90e8\u5206\u7684 s \u504f\u632f\u6ce2\uff08\u4e0e\u5165\u5c04\u9762\u6b63\u4ea4\u7684\u504f\u632f\uff09\u5c06\u88ab\u53cd\u5c04\u3002\u66f4\u6539\u9ad8\u65af\u5149\u675f\u7684\u5149\u6591\u534a\u5f84\u5c06\u6539\u53d8\u504f\u632f\u7684\u5206\u8fa8\u7387\u3002\n\n\u672c\u4f8b\u8ba1\u7b97\u4e0d\u540c\u9ad8\u65af\u5149\u675f\u5149\u6591\u534a\u5f84\u7684\u53cd\u5c04\u548c\u900f\u5c04\u5149\u8c31\u3002");

    model.label("polarizing_beam_splitter.mph");

    model.result("pg4").run();

    model.setExpectedComputationTime("2 \u5206\u949f");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").label("\u504f\u632f\u5149\u675f\u5206\u675f\u5668");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///polarizing_beam_splitter");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u4e00\u675f\u9ad8\u65af\u5149\u675f\u5165\u5c04\u5230\u5185\u5d4c\u5230\u73bb\u7483\u6750\u6599\u68f1\u955c\u7684 45 \u5ea6\u8584\u819c\u53e0\u5c42\u3002\u8584\u819c\u53e0\u5c42\u91c7\u7528\u4f4e\u548c\u9ad8\u6298\u5c04\u7387\u6750\u6599\u4ea4\u66ff\u6392\u5217\u7684\u8bbe\u8ba1\uff0c\u6ce2\u5c06\u4f1a\u5728\u6bcf\u4e2a\u5185\u90e8\u754c\u9762\u7684 Brewster \u89d2\u5904\u8fdb\u884c\u6298\u5c04\uff0c\u56e0\u6b64\u5927\u90e8\u5206\u7684 p \u504f\u632f\u6ce2\uff08\u5165\u5c04\u9762\u5185\u504f\u632f\uff09\u53d1\u751f\u900f\u5c04\uff0c\u800c\u5927\u90e8\u5206\u7684 s \u504f\u632f\u6ce2\uff08\u4e0e\u5165\u5c04\u9762\u6b63\u4ea4\u7684\u504f\u632f\uff09\u53d1\u751f\u53cd\u5c04\u3002\u66f4\u6539\u9ad8\u65af\u5149\u675f\u7684\u5149\u6591\u534a\u5f84\u5c06\u6539\u53d8\u504f\u632f\u7684\u5206\u8fa8\u7387\u3002\n\n\u672c App \u8ba1\u7b97\u4e86\u4e0d\u540c\u9ad8\u65af\u5149\u675f\u5149\u6591\u534a\u5f84\u7684\u53cd\u5c04\u5149\u8c31\u548c\u900f\u5c04\u5149\u8c31\u3002");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u6298\u5c04\u7387\u5206\u5e03");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("pg1").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("pg1")
         .label("\u6298\u5c04\u7387\u5206\u5e03");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u7535\u573a\u6a21");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("pg1").label("\u7535\u573a\u6a21");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2")
         .label("\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg1").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("pg1")
         .label("\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387");

    model.title("\u504f\u632f\u5149\u675f\u5206\u675f\u5668");

    model
         .description("\u4e00\u675f\u9ad8\u65af\u5149\u675f\u5165\u5c04\u5230\u4e00\u4e2a\u5d4c\u5165\u73bb\u7483\u6750\u6599\u68f1\u955c\u7684 45 \u5ea6\u8584\u819c\u53e0\u5c42\u4e0a\u3002\u8fd9\u4e2a\u8584\u819c\u53e0\u5c42\u91c7\u7528\u9ad8\u548c\u4f4e\u6298\u5c04\u7387\u6750\u6599\u4ea4\u66ff\u6392\u5217\u7684\u8bbe\u8ba1\uff0c\u6ce2\u5c06\u4f1a\u5728\u6bcf\u4e2a\u5185\u90e8\u754c\u9762\u4ee5 Brewster \u89d2\u8fdb\u884c\u6298\u5c04\u3002\u56e0\u6b64\u5927\u90e8\u5206\u7684 p \u504f\u632f\u6ce2\uff08\u504f\u632f\u4e0e\u5165\u5c04\u9762\u4e00\u81f4\uff09\u4f1a\u53d1\u751f\u900f\u5c04\uff0c\u800c\u5927\u90e8\u5206\u7684 s \u504f\u632f\u6ce2\uff08\u4e0e\u5165\u5c04\u9762\u6b63\u4ea4\u7684\u504f\u632f\uff09\u5c06\u88ab\u53cd\u5c04\u3002\u66f4\u6539\u9ad8\u65af\u5149\u675f\u7684\u5149\u6591\u534a\u5f84\u5c06\u6539\u53d8\u504f\u632f\u7684\u5206\u8fa8\u7387\u3002\n\n\u672c\u4f8b\u8ba1\u7b97\u4e0d\u540c\u9ad8\u65af\u5149\u675f\u5149\u6591\u534a\u5f84\u7684\u53cd\u5c04\u548c\u900f\u5c04\u5149\u8c31\u3002\n\n\u5f53\u7528\u6237\u66f4\u6539\u8bbe\u8ba1\u53c2\u6570\u65f6\uff0c\u8be5 App \u4f1a\u81ea\u52a8\u8ba1\u7b97\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6240\u9700\u7684\u76f8\u8868\u8fbe\u5f0f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
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
    model.sol("sol2").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();

    model.label("polarizing_beam_splitter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
