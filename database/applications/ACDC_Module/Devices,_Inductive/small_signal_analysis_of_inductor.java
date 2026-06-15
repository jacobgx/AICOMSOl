/*
 * small_signal_analysis_of_inductor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class small_signal_analysis_of_inductor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/mf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("dr", "3[cm]", "\u57df\u534a\u5f84");
    model.param().set("il", "2[cm]", "\u7535\u611f\u5668\u957f\u5ea6");
    model.param().set("cr", "5[mm]", "\u82af\u534a\u5f84");
    model.param().set("fr", "0.5[mm]", "\u5706\u89d2\u534a\u5f84");
    model.param().set("coor", "10.5[mm]", "\u7ebf\u5708\uff0c\u5916\u534a\u5f84");
    model.param().set("coir", "7.5[mm]", "\u7ebf\u5708\uff0c\u5185\u534a\u5f84");
    model.param().set("cwc", "pi*(0.05[mm])^2", "\u7ebf\u5708\uff0c\u5bfc\u7ebf\u622a\u9762\u79ef");
    model.param().set("cn", "1000", "\u7ebf\u5708\uff0c\u531d\u6570");
    model.param().set("f0", "10[kHz]", "\u5de5\u4f5c\u9891\u7387");
    model.param().set("csigma", "5e7[S/m]", "\u7ebf\u5708\uff0c\u5bfc\u7ebf\u7535\u5bfc\u7387");
    model.param().set("cIdc", "10[mA]", "\u76f4\u6d41\u504f\u7f6e\u7535\u6d41");
    model.param().set("cIac", "1[mA]", "\u4ea4\u6d41\u6270\u52a8\u7535\u6d41");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "dr");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"3*cr", "il*1.5"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-il*1.5/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"coor-coir", "il"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"coir", "-il/2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"(coor-coir)*2", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "il*1.1", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"coir*0.8", "-il/2*1.1"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 2, 3);
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r3", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "fr");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").label("\u975e\u7ebf\u6027\u94c1\u82af");
    model.component("comp1").physics("mf").feature("als1").selection().set(2);
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").feature("als1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1")
         .set("sigma", new int[]{1000, 0, 0, 0, 1000, 0, 0, 0, 1000});
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4);
    model.component("comp1").physics("mf").feature("coil1").set("CoilName", "coil");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", "cn");
    model.component("comp1").physics("mf").feature("coil1").set("sigmaCoil", "csigma");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "cwc");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "cIdc");
    model.component("comp1").physics("mf").feature("coil1").create("hp1", "CoilHarmonicPerturbation", -1);
    model.component("comp1").physics("mf").feature("coil1").feature("hp1").set("ICoil", "cIac");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").label("Low Carbon Steel 1006");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"8.41[MS/m]", "0", "0", "0", "8.41[MS/m]", "0", "0", "0", "8.41[MS/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1[1]", "0", "0", "0", "1[1]", "0", "0", "0", "1[1]"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("funcnametable", new String[][]{{"BHCurve1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"19.7407997395833", "0.0963091145833333"}, 
         {"38.5601479166667", "0.188022916666667"}, 
         {"55.53659296875", "0.27054609375"}, 
         {"69.7486833333333", "0.339283333333333"}, 
         {"80.5960114583333", "0.39121953125"}, 
         {"88.7623458333333", "0.429660416666667"}, 
         {"95.2524989583333", "0.459491927083333"}, 
         {"101.071283333333", "0.4856"}, 
         {"107.05963984375", "0.512055989583333"}, 
         {"113.403022916667", "0.539672916666667"}, 
         {"120.123015364583", "0.56844921875"}, 
         {"127.2412", "0.598383333333333"}, 
         {"134.780091666667", "0.62946640625"}, 
         {"142.765933333333", "0.661660416666667"}, 
         {"151.2259", "0.694920052083333"}, 
         {"160.187166666667", "0.7292"}, 
         {"169.678080208333", "0.764441927083333"}, 
         {"179.731675", "0.800535416666667"}, 
         {"190.382157291667", "0.83735703125"}, 
         {"201.663733333333", "0.874783333333333"}, 
         {"213.6120859375", "0.912679427083333"}, 
         {"226.268804166667", "0.950864583333333"}, 
         {"239.676953645833", "0.989146614583333"}, 
         {"253.8796", "1.02733333333333"}, 
         {"268.921667708333", "1.06522604166667"}, 
         {"284.855516666667", "1.1026"}, 
         {"301.735365625", "1.13922395833333"}, 
         {"319.615433333333", "1.17486666666667"}, 
         {"338.552277864583", "1.20930625"}, 
         {"358.611814583333", "1.24235833333333"}, 
         {"379.862298177083", "1.27384791666667"}, 
         {"402.371983333333", "1.3036"}, 
         {"426.212070052083", "1.33146223958333"}, 
         {"451.465539583333", "1.35737291666667"}, 
         {"478.218318489583", "1.38129296875"}, 
         {"506.556333333333", "1.40318333333333"}, 
         {"536.569219270833", "1.42303125"}, 
         {"568.361445833333", "1.44092916666667"}, 
         {"602.041191145833", "1.45699583333333"}, 
         {"637.716633333333", "1.47135"}, 
         {"675.500618489583", "1.4841203125"}, 
         {"715.524664583333", "1.495475"}, 
         {"757.924957552083", "1.5055921875"}, 
         {"802.837683333333", "1.51465"}, 
         {"850.404904947917", "1.52282291666667"}, 
         {"900.79219375", "1.53027083333333"}, 
         {"954.170998177083", "1.53715"}, 
         {"1010.71276666667", "1.54361666666667"}, 
         {"1070.59634661458", "1.54981510416667"}, 
         {"1134.03018125", "1.55584166666667"}, 
         {"1201.23011276042", "1.56178072916667"}, 
         {"1272.41198333333", "1.56771666666667"}, 
         {"1347.80094921875", "1.57372395833333"}, 
         {"1427.65942291667", "1.5798375"}, 
         {"1512.25913098958", "1.58608229166667"}, 
         {"1601.8718", "1.59248333333333"}, 
         {"1696.78088307292", "1.59905989583333"}, 
         {"1797.31673958333", "1.60580833333333"}, 
         {"1903.82145546875", "1.61271927083333"}, 
         {"2016.63711666667", "1.61978333333333"}, 
         {"2136.12057135417", "1.62698984375"}, 
         {"2262.68771666667", "1.63432291666667"}, 
         {"2396.76921197917", "1.64176536458333"}, 
         {"2538.79571666667", "1.6493"}, 
         {"2689.216475", "1.65690833333333"}, 
         {"2848.55507083333", "1.66456666666667"}, 
         {"3017.35367291667", "1.67225"}, 
         {"3196.15445", "1.67993333333333"}, 
         {"3385.52296744792", "1.68759453125"}, 
         {"3586.11837708333", "1.69522291666667"}, 
         {"3798.62322734375", "1.70281067708333"}, 
         {"4023.72006666667", "1.71035"}, 
         {"4262.12089895833", "1.7178359375"}, 
         {"4514.65555", "1.725275"}, 
         {"4782.18330104167", "1.7326765625"}, 
         {"5065.56343333333", "1.74005"}, 
         {"5365.69230885417", "1.74740885416667"}, 
         {"5683.6146125", "1.75478333333333"}, 
         {"6020.41210989583", "1.7622078125"}, 
         {"6377.16656666667", "1.76971666666667"}, 
         {"6755.00643177083", "1.77734348958333"}, 
         {"7155.2468875", "1.78511875"}, 
         {"7579.24979947917", "1.79307213541667"}, 
         {"8028.37703333333", "1.80123333333333"}, 
         {"8504.04922526041", "1.80963151041667"}, 
         {"9007.92209375", "1.81829375"}, 
         {"9541.71012786458", "1.82724661458333"}, 
         {"10107.1278166667", "1.83651666666667"}, 
         {"10705.9636359375", "1.84612630208333"}, 
         {"11340.3020083333", "1.85608125"}, 
         {"12012.3013432292", "1.86638307291667"}, 
         {"12724.12005", "1.87703333333333"}, 
         {"13478.0096830729", "1.88802942708333"}, 
         {"14276.5943770833", "1.89935208333333"}, 
         {"15122.5914117187", "1.91097786458333"}, 
         {"16018.7180666667", "1.92288333333333"}, 
         {"16967.8088835938", "1.93504114583333"}, 
         {"17973.1674520833", "1.94740833333333"}, 
         {"19038.2146236979", "1.95993802083333"}, 
         {"20166.37125", "1.97258333333333"}, 
         {"21361.20580625", "1.98529453125"}, 
         {"22626.8772625", "1.99801041666667"}, 
         {"23967.6922125", "2.01066692708333"}, 
         {"25387.95725", "2.0232"}, 
         {"26892.1648177083", "2.03554635416667"}, 
         {"28485.5507541667", "2.04764583333333"}, 
         {"30173.536746875", "2.0594390625"}, 
         {"31961.5444833333", "2.07086666666667"}, 
         {"33855.22961875", "2.08187630208333"}, 
         {"35861.1836791667", "2.09244375"}, 
         {"37986.2321583333", "2.10255182291667"}, 
         {"40237.20055", "2.11218333333333"}, 
         {"42621.2088973958", "2.121328125"}, 
         {"45146.5554416667", "2.13000416666667"}, 
         {"47821.8329734375", "2.13823645833333"}, 
         {"50655.6342833333", "2.14605"}, 
         {"53656.9229768229", "2.1534765625"}, 
         {"56836.14591875", "2.160575"}, 
         {"60204.1207888021", "2.1674109375"}, 
         {"63771.6652666667", "2.17405"}, 
         {"67550.0638614583", "2.1805578125"}, 
         {"71552.4684", "2.187"}, 
         {"75792.4975385417", "2.1934421875"}, 
         {"80283.7699333333", "2.19995"}, 
         {"85040.4919427083", "2.2065875"}, 
         {"90079.2207333333", "2.2134125"}, 
         {"95417.1011739583", "2.22048125"}, 
         {"101071.278133333", "2.22785"}, 
         {"107059.636354167", "2.23557161458333"}, 
         {"113403.020075", "2.24368541666667"}, 
         {"120123.013408333", "2.25222734375"}, 
         {"127241.200466667", "2.26123333333333"}, 
         {"134780.096808073", "2.27074036458333"}, 
         {"142765.943772917", "2.28078958333333"}, 
         {"151225.914147135", "2.29142317708333"}, 
         {"160187.180716667", "2.30268333333333"}, 
         {"169678.088888802", "2.31461197916667"}, 
         {"179731.67455625", "2.32725"}, 
         {"190382.146233073", "2.34063802083333"}, 
         {"201663.712433333", "2.35481666666667"}, 
         {"213612.057913021", "2.36982916666667"}, 
         {"226268.772395833", "2.38572916666667"}, 
         {"239676.921847396", "2.40257291666667"}, 
         {"253879.572233333", "2.42041666666667"}, 
         {"268790.53303474", "2.43915367374234"}, 
         {"283806.587794583", "2.45802522327207"}, 
         {"298195.263571302", "2.4761096077099"}, 
         {"311224.087423333", "2.49248511950988"}, 
         {"323854.862406094", "2.50835940392021"}, 
         {"343826.495562917", "2.53345751736562"}, 
         {"380572.169934115", "2.579633869065"}, 
         {"443525.06856", "2.6587428682372"}, 
         {"539061.160336875", "2.77879706186916"}, 
         {"661327.557585", "2.9324415480199"}, 
         {"801414.158480625", "3.10847956251649"}, 
         {"950410.8612", "3.29571434118602"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").selection().set(4);
    model.component("comp1").material("mat2").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("mur", "mf.normB/(mu0_const*mf.normH)");
    model.component("comp1").variable("var1").descr("mur", "\u76f8\u5bf9\u78c1\u5bfc\u7387");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "dr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "dr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "cIdc", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,1,50)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mA", 0);
    model.study("std1").feature("frlin").set("punit", "kHz");
    model.study("std1").feature("frlin").set("plist", "f0");
    model.study("std1").feature("frlin").set("useparam", true);
    model.study("std1").feature("frlin").setIndex("pname_aux", "dr", 0);
    model.study("std1").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("frlin").setIndex("pname_aux", "dr", 0);
    model.study("std1").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("frlin").setIndex("pname_aux", "cIdc", 0);
    model.study("std1").feature("frlin").setIndex("plistarr_aux", "range(1,1,50)", 0);
    model.study("std1").feature("frlin").setIndex("punit_aux", "mA", 0);
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("evalmethodactive", "off");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("evalmethodactive", "off");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("evalmethodactive", "off");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("evalmethodactive", "off");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("evalmethodactive", "off");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("evalmethodactive", "off");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("evalmethodactive", "off");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("evalmethodactive", "off");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("evalmethodactive", "off");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("evalmethodactive", "off");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("evalmethodactive", "off");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("evalmethodactive", "off");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("evalmethodactive", "off");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("evalmethodactive", "off");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("evalmethodactive", "off");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("evalmethodactive", "off");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("evalmethodactive", "off");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7ebf\u5708\u7535\u611f");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mf.LCoil_coil"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u611f"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"H"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "cIdc");
    model.result("pg3").feature("glob1").set("xdataunit", "mA");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "mur");
    model.result("pg1").feature("surf1").set("descr", "\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.result("pg1").feature("surf1").set("evalmethod", "linpoint");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();

    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "cIdc + linper(cIac)");
    model.component("comp1").physics("mf").feature("coil1").feature("hp1").active(false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.title("\u7535\u611f\u5668\u4e2d\u7684\u5c0f\u4fe1\u53f7\u5206\u6790");

    model
         .description("\u7535\u611f\u5668\u4e2d\u5fc3\u5185\u90e8\u662f\u78c1\u6027\u6750\u6599\uff0c\u4f1a\u5f71\u54cd\u7535\u611f\u3002\u5982\u679c\u78c1\u6027\u6750\u6599\u662f\u975e\u7ebf\u6027\u7684\uff0c\u81ea\u611f\u53d6\u51b3\u4e8e\u901a\u8fc7\u7535\u611f\u5668\u7684\u7535\u6d41\u3002\u672c\u4f8b\u5206\u6790\u4e86\u5177\u6709\u975e\u7ebf\u6027\u94c1\u5fc3\u7684\u81ea\u611f\u73b0\u8c61\u4e2d\u7684\u5c0f\u4fe1\u53f7\u7279\u6027\uff0c\u5e76\u7814\u7a76\u4e86\u76f4\u6d41\u7535\u6d41\u600e\u6837\u5f71\u54cd\u5c0f\u4fe1\u53f7\u81ea\u611f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("small_signal_analysis_of_inductor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
