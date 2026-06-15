/*
 * inductor_in_circuit.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class inductor_in_circuit {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Circuits");

    model.param().set("t", "0[s]", "\u7a33\u6001\u89e3\u7684\u65f6\u95f4");
    model.param().set("N", "1e3", "\u7ebf\u5708\u531d\u6570");
    model.param().set("d_coil", "0.1[mm]", "\u7ebf\u5708\u7ebf\u5f84");
    model.param().set("sigma_coil", "5e7[S/m]", "\u5bfc\u7ebf\u7535\u5bfc\u7387");
    model.param().set("Vappl", "15[V]", "\u7535\u6e90\u7535\u538b");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 30);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{15, 30});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -15});
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{3, 20});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{7.5, -10});
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{6, 22});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{6, -11});
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r1(1)", 2, 3);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r2(1)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r3(1)", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(2);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4);
    model.component("comp1").physics("mf").create("coil2", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil2").selection().set(4);
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").create("VIN", "VoltageSource", -1);
    model.component("comp1").physics("cir").create("VCC", "VoltageSource", -1);
    model.component("comp1").physics("cir").create("RG", "Resistor", -1);
    model.component("comp1").physics("cir").create("CIN", "Capacitor", -1);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").create("RE", "Resistor", -1);
    model.component("comp1").physics("cir").create("COUT", "Capacitor", -1);
    model.component("comp1").physics("cir").create("RL", "Resistor", -1);
    model.component("comp1").physics("cir").create("Q1", "BjtNpn", -1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");

    model.component("comp1").view("view1").axis().set("xmin", -38.05307388305664);
    model.component("comp1").view("view1").axis().set("xmax", 68.0481948852539);
    model.component("comp1").view("view1").axis().set("ymin", -39.385169982910156);
    model.component("comp1").view("view1").axis().set("ymax", 39.385169982910156);

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

    model.component("comp1").physics("mf").feature("init1").set("A", new String[][]{{"0"}, {"1[uWb/m^2]*r"}, {"0"}});
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").feature("als1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1")
         .set("sigma", new int[][]{{1000}, {0}, {0}, {0}, {1000}, {0}, {0}, {0}, {1000}});
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", 0.1);
    model.component("comp1").physics("mf").feature("coil1").set("N", "N");
    model.component("comp1").physics("mf").feature("coil1").set("sigmaCoil", "sigma_coil");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "Diameter");
    model.component("comp1").physics("mf").feature("coil1").set("coilWindDiameter", "d_coil");
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf").feature("coil2").set("CoilName", "1");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", 0.1);
    model.component("comp1").physics("mf").feature("coil2")
         .set("circuitConnectionStatus", "circuitConnectedProperly");
    model.component("comp1").physics("mf").feature("coil2")
         .set("derivedInformationMessage", "\u5df2\u8fde\u63a5\u5230 \u7535\u8def\uff0c\u5916\u90e8 I vs. U 1");
    model.component("comp1").physics("mf").feature("coil2").set("toBeReferencedElectricalCircuitTag", "cir");
    model.component("comp1").physics("mf").feature("coil2").set("N", "N");
    model.component("comp1").physics("mf").feature("coil2").set("sigmaCoil", "sigma_coil");
    model.component("comp1").physics("mf").feature("coil2").set("AreaFrom", "Diameter");
    model.component("comp1").physics("mf").feature("coil2").set("coilWindDiameter", "d_coil");
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.mf.VCoil_1");
    model.component("comp1").physics("cir").feature("VIN").set("Connections", new int[][]{{1}, {0}});
    model.component("comp1").physics("cir").feature("VIN").set("sourceType", "SineSource");
    model.component("comp1").physics("cir").feature("VIN").set("value", "1.0");
    model.component("comp1").physics("cir").feature("VIN").set("offset", "0.0");
    model.component("comp1").physics("cir").feature("VIN").set("freq", "10000.0");
    model.component("comp1").physics("cir").feature("VIN").label("\u7535\u538b\u6e90 VIN");
    model.component("comp1").physics("cir").feature("VCC").set("Connections", new int[][]{{4}, {0}});
    model.component("comp1").physics("cir").feature("VCC").set("value", "15.0");
    model.component("comp1").physics("cir").feature("VCC").label("\u7535\u538b\u6e90 VCC");
    model.component("comp1").physics("cir").feature("RG").set("R", "100.0");
    model.component("comp1").physics("cir").feature("RG").set("Connections", new int[][]{{1}, {2}});
    model.component("comp1").physics("cir").feature("RG").label("\u7535\u963b RG");
    model.component("comp1").physics("cir").feature("CIN").set("C", 9.999999999999999E-6);
    model.component("comp1").physics("cir").feature("CIN").set("Connections", new int[][]{{2}, {3}});
    model.component("comp1").physics("cir").feature("CIN").label("\u7535\u5bb9\u5668 CIN");
    model.component("comp1").physics("cir").feature("R1").set("R", "47000.0");
    model.component("comp1").physics("cir").feature("R1").set("Connections", new int[][]{{4}, {3}});
    model.component("comp1").physics("cir").feature("R1").label("\u7535\u963b R1");
    model.component("comp1").physics("cir").feature("R2").set("R", "10000.0");
    model.component("comp1").physics("cir").feature("R2").set("Connections", new int[][]{{3}, {0}});
    model.component("comp1").physics("cir").feature("R2").label("\u7535\u963b R2");
    model.component("comp1").physics("cir").feature("RE").set("R", "1000.0");
    model.component("comp1").physics("cir").feature("RE").set("Connections", new int[][]{{7}, {0}});
    model.component("comp1").physics("cir").feature("RE").label("\u7535\u963b RE");
    model.component("comp1").physics("cir").feature("COUT").set("C", 9.999999999999999E-6);
    model.component("comp1").physics("cir").feature("COUT").set("Connections", new int[][]{{5}, {6}});
    model.component("comp1").physics("cir").feature("COUT").label("\u7535\u5bb9\u5668 COUT");
    model.component("comp1").physics("cir").feature("RL").set("R", "10000.0");
    model.component("comp1").physics("cir").feature("RL").set("Connections", new int[][]{{6}, {0}});
    model.component("comp1").physics("cir").feature("RL").label("\u7535\u963b RL");
    model.component("comp1").physics("cir").feature("Q1").set("Connections", new int[][]{{5}, {3}, {7}});
    model.component("comp1").physics("cir").feature("Q1").set("BF", "260.0");
    model.component("comp1").physics("cir").feature("Q1").set("BR", 6.1);
    model.component("comp1").physics("cir").feature("Q1").set("CJC", 7.5E-12);
    model.component("comp1").physics("cir").feature("Q1").set("CJE", 2.0E-11);
    model.component("comp1").physics("cir").feature("Q1").set("IKF", 0.3);
    model.component("comp1").physics("cir").feature("Q1").set("IS", 1.5000000000000002E-14);
    model.component("comp1").physics("cir").feature("Q1").set("ISC", "0.0");
    model.component("comp1").physics("cir").feature("Q1").set("ISE", 1.5000000000000002E-14);
    model.component("comp1").physics("cir").feature("Q1").set("MJC", 0.35);
    model.component("comp1").physics("cir").feature("Q1").set("MJE", 0.4);
    model.component("comp1").physics("cir").feature("Q1").set("NC", "2.0");
    model.component("comp1").physics("cir").feature("Q1").set("NE", 1.3);
    model.component("comp1").physics("cir").feature("Q1").set("RB", "10.0");
    model.component("comp1").physics("cir").feature("Q1").set("RC", "1.0");
    model.component("comp1").physics("cir").feature("Q1").set("VAF", "75.0");
    model.component("comp1").physics("cir").feature("Q1").set("VJC", 0.75);
    model.component("comp1").physics("cir").feature("Q1").set("VJE", 0.75);
    model.component("comp1").physics("cir").feature("Q1").label("NPN \u53cc\u6781\u7ed3\u578b\u6676\u4f53\u7ba1 Q1");

    model.component("comp1").mesh("mesh1").feature("size").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");

    model.sol().create("sol1");
    model.sol("sol1").attach("std1");

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().create("pg1", "PlotGroup2D");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").feature("str1").create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");

    model.sol("sol1").createAutoSequence("std1");

    model.study("std1").runNoGen();

    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("resolution", "normal");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("resolution", "normal");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("resolution", "fine");

    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 5, 1, 0);
    model.component("comp1").physics("cir").feature("VCC").set("value", "Vappl");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mf/coil2"});
    model.study("std1").feature("stat").setSolveFor("/physics/cir", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mf/coil2", "cir"});
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tlist", "range(0,5e-6,5e-4)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-4");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"mf/coil1"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "t", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "s", 0);
    model.study("std2").feature("stat").setIndex("pname", "t", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "s", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vappl", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1,15)", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"mf/coil1"});
    model.study("std2").showAutoSequences("all");
    model.study("std2").feature("stat").set("usestol", true);
    model.study("std2").feature("stat").set("stol", "1e-6");

    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("t1").set("atolglobal", "1e-6");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.03);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("solutionparams", "parent");
    model.result("pg3").feature("con1").set("expr", "mf.Psi");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("number", 10);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("color", "custom");
    model.result("pg3").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg3").feature("con1").set("resolution", "fine");
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").feature("con1").set("inheritplot", "surf1");
    model.result("pg3").feature("con1").feature().create("filt1", "Filter");
    model.result("pg3").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf) 1");
    model.result("pg4").set("data", "rev2");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("solutionparams", "parent");
    model.result("pg4").feature("con1").set("expr", "mf.Psi");
    model.result("pg4").feature("con1").set("titletype", "none");
    model.result("pg4").feature("con1").set("number", 10);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "custom");
    model.result("pg4").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg4").feature("con1").set("resolution", "fine");
    model.result("pg4").feature("con1").set("inheritcolor", false);
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg4").feature("con1").set("inheritplot", "vol1");
    model.result("pg4").feature("con1").feature().create("filt1", "Filter");
    model.result("pg4").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5")
         .setIndex("looplevel", new int[]{81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, 0);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u538b (V)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "cir.VIN.v", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "Voltage across device VIN", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5668\u4ef6 VIN \u4e0a\u7684\u7535\u538b", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "cir.IvsU1.v", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "Voltage across device IvsU1", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5668\u4ef6 IvsU1 \u4e0a\u7684\u7535\u538b", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "cir.RL.v", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "Voltage across device RL", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5668\u4ef6 RL \u4e0a\u7684\u7535\u538b", 2);
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u538b");

    model.title("\u653e\u5927\u7535\u8def\u4e2d\u7684\u7535\u611f\u5668");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u7535\u5b50\u7535\u8def\u7684\u4eff\u771f\u4e0e\u6709\u9650\u5143\u7684\u4eff\u771f\u7ec4\u5408\u8d77\u6765\u3002\u6709\u9650\u5143\u6a21\u578b\u662f\u4e00\u4e2a\u542b\u975e\u7ebf\u6027\u78c1\u82af\u548c 1000\u00a0\u531d\u7ebf\u5708\u7684\u7535\u611f\u5668\uff0c\u5176\u4e2d\u531d\u6570\u91c7\u7528\u5206\u5e03\u7535\u6d41\u6280\u672f\u5efa\u6a21\u3002\n\u7535\u8def\u4f5c\u4e3a SPICE \u7f51\u8868\u5bfc\u5165 COMSOL Multiphysics \u4e2d\uff0c\u4ece\u800c\u5408\u5e76\u7535\u611f\u5668\u6a21\u578b\u4e0e\u7535\u8def\u5143\u4ef6\uff0c\u5f62\u6210\u5e38\u5fae\u5206\u65b9\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("inductor_in_circuit.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
